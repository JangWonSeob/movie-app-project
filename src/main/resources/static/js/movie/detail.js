const MOVIE_ID = location.pathname.split('/')[3];

var onClickPageIndex = function (pageIndex) {
    getCommentList(pageIndex);
}

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

var drawingCommentList = function (list, totalCount, pageIndex) {
    const $comments = $('#comment_list');

    $comments.empty();

    let html = '';

    list.forEach(function (comment) {
        html += '<div style="margin: 0px 0px;">';
        html += '   <div style="display: flex; justify-content: space-between"><b>' + comment.regNickname + '</b><span>' + comment.regDtText + '</span></div>';
        html += '   <div style="margin: 5px 0px">' + comment.contents.replaceAll("\n", "<br/>") + '</div>';
        html += '</div>';
        html += '<hr/>';
    })

    $comments.append(html);

    drawIngPager(totalCount, pageIndex);
}

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

var setComment = function (commentId) {
    const url = '/api/comment/movie/set.api';

    const contents = $('#comment_contents').val() || '';
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

var notLoginContents = function () {
    if (!confirm('로그인 후 이용해주세요.\n' +
                '로그인 페이지로 이동하시겠습니까?')) {
        return false;
    }

    location.href = '/user/login';
}

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