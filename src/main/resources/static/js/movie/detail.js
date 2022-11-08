const MOVIE_ID = location.pathname.split('/')[3];

// 페이지 이동 이벤트
var onClickPageIndex = function (pageIndex) {
    getCommentList(pageIndex);
}

// 댓글 목록 가져오기
var getCommentList = function (pageIndex) {
    const url = '/api/comment/movie/gets.api';

    const param = {
        tableId: MOVIE_ID,
        pageIndex: pageIndex
    };

    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        console.log(data);
        drawingCommentList(data.list, data.totalCount, pageIndex);
    })
}

var getReCommentList = function (commentId) {
    const url = '/api/comment/movie/re/gets.api';
    const $rePageIndex = $('#pageIndex_' + commentId);
    console.log(parseInt($rePageIndex.val()) + 1);
    const rePageIndex = parseInt($rePageIndex.val()) + 1 || 0;

    const param = {
        tableId: MOVIE_ID,
        searchCommentId: commentId,
        pageIndex: rePageIndex
    };

    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        console.log(data);
        drawingReCommentList(commentId, data.list, data.totalCount, rePageIndex);
    })
}

// 댓글 리스트 그려주기
var drawingCommentList = function (list, totalCount, pageIndex) {
    const $comments = $('#comment_list');

    $comments.empty();

    let html = '';

    list.forEach(function (comment) {
        html += '<div style="margin: 0px 0px;">';
        html += '   <div style="display: flex; justify-content: space-between"><b>' + comment.regNickname + '</b><span>' + comment.regDtText + '</span></div>';
        html += '   <div style="margin: 5px 0px">' + comment.contents.replaceAll("\n", "<br/>") + '</div>';
        html += '   <button type="button" style="font-size: 13px;" id="' + comment.id + '" onclick="onClickShowReCommentWrite()">' + '답글' + '</button>'
        html += '   <div class="none" style="justify-content: space-between; margin: 5px 0;" id="recomment_' + comment.id + '">';
        html += '        <textarea style="height: 15%; width: 100%" id="comment_contents_' + comment.id + '" placeholder="댓글을 입력해주세요."></textarea>';
        html += '        <button type="button" style="min-width: 50px" id="comment_set_' + comment.id + '" onclick="onClickReCommentSet(' + comment.id + ')">등록</button>'
        html += '   </div>';
        // if (comment.childCommentCount > 0) {
            html += '<div>'
            html += '   <button type="button" style="font-size: 13px; margin: 5px 0; background: transparent; border: none" onclick="onClickShowReComment(' + comment.id + ')">' + '댓글이 ' + comment.childCommentCount + '개 존재합니다.' + '</button>'
            html += '   <input type="hidden" id="pageIndex_' + comment.id + '" value="0"/>';
            html += '   <div id="re_comment_' + comment.id +'"></div>';
            html += '</div>'
        // }

        html += '</div>';
        html += '<hr/>';
    })

    $comments.append(html);
    drawIngPager(totalCount, pageIndex);
}

// 대댓글 그려주기
var drawingReCommentList = function (commentId, list, totalCount, pageIndex) {
    const $reComments = $('#re_comment_' + commentId);

    if (pageIndex < 2) {
        $reComments.empty();
    }

    let html = '';

    list.forEach(function (comment) {
        html += '<div style="margin: 0px 0px; margin-left: 20px">';
        html += '   <div style="display: flex; justify-content: space-between"><b>' + comment.regNickname + '</b><span>' + comment.regDtText + '</span></div>';
        html += '   <div style="margin: 5px 0px">' + comment.contents.replaceAll("\n", "<br/>") + '</div>';
        html += '</div>';
    })

    $reComments.append(html);
}

// 대댓글 리스트 이벤트
var onClickShowReComment = function (commentId) {

    getReCommentList(commentId);
}

// 대댓글 작성 INPUT 활성화 이벤트
var onClickShowReCommentWrite = function () {
    const id = event.target.id;
    const loginYn = $('#loginYn').val();

    if (loginYn !== 'true'){
        alert(' 로그인 후 이용해주세요. ');
        return false;
    }

    const $recommentId = $('#recomment_' + id);

    if ($recommentId.hasClass('none') && !$recommentId.hasClass('flex')) {
        $recommentId.removeClass('none');
        $recommentId.addClass('flex');
    } else if (!$recommentId.hasClass('none') && $recommentId.hasClass('flex')) {
        $recommentId.addClass('none');
        $recommentId.removeClass('flex');
    } else {
        $recommentId.removeClass();
    }
}

// 댓글 작석 이벤트
var onClickReCommentSet = function (commentId) {
    setComment(commentId);
}

// 즐겨찾기 이벤트
var setBookMark = function () {
    const url = '/api/book-mark/movie/set.api';

    const param = {
        tableId: MOVIE_ID
    };

    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        console.log(data);
        if (data.bookMarkYn) {
            $('#movie-bookmark').css('background-color', 'yellow');
        } else {
            $('#movie-bookmark').css('background-color', 'white');
        }
    })
}



// 댓글 작성 이벤트
var setComment = function (commentId) {
    const url = '/api/comment/movie/set.api';

    let contents = '';
    if (commentId < 1) {
        contents = $('#comment_contents').val() || '';
    } else {
        contents = $('#comment_contents_' + commentId).val() || '';
    }


    if (contents.length < 1) {
        alert('댓글 내용을 입력해주세요.');
        return false;
    }

    if (!confirm(' 댓글을 등록하시겠습니까? ')) {
        return false;
    }

    const param = {
        tableId: MOVIE_ID,
        commentId: commentId,
        contents: contents
    };

    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        alert('등록되었습니다.');
        location.reload();
    })
}

// 비 로그인 이벤트
var notLoginContents = function () {
    if (!confirm('로그인 후 이용해주세요.\n' +
                '로그인 페이지로 이동하시겠습니까?')) {
        return false;
    }

    location.href = '/user/login';
}

// 페이징
var drawIngPager = function (totalCount, pageIndex) {

    PAGER.drawingPager(totalCount, pageIndex);

}

$(function () {
    getCommentList(1);

    $('#movie-bookmark').on('click', function () {
        setBookMark();
    })

    $('#comment_set').on('click', function () {
        setComment(0);
    })

    $('#not_login_contents').on('click', function () {
        notLoginContents();
    })
});