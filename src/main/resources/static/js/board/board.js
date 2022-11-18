let PAGE_INDEX = 1;
let PAGE_SIZE = 10;
let SEARCH_GENRE = '';

let LIST = [];
let TOTAL_COUNT = 0;

// BOARD_ID
// http://localhost:8080/board/detail/118
// split('/')[3] == 118
const BOARD_ID = location.pathname.split('/')[3];

var setLikes = function () {
    const url = '/api/likes/set.api';

    const param = {
        boardId: BOARD_ID
    };

    console.log("boardId", BOARD_ID)

    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        console.log(data);
        // 원하는 색깔을 넣어주세요.
        // 누르기전 기본색은 css나 부트스트랩으로 하시면 됩니다.
        if (data.likesYn) { // like눌렀을때
            // $('#board-like').css('background-color', '#d3d3d3');
            // $('#board-like-img').css('background-color', '#d3d3d3');
            $('#board-like-img').css('color', '#FFDF65');
            $('#board-unlike-img').css('color', '#d3d3d3');
        } else {
            // $('#board-like').css('background-color', 'white');
            // $('#board-like-img').css('background-color', 'white');
            $('#board-like-img').css('color', '#d3d3d3');
        }
    })
}

var setUnlikes = function () {
    const url = '/api/unlikes/set.api';
    const param = {
        boardId: BOARD_ID
    };
    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        console.log(data);
        // 원하는 색깔을 넣어주세요.
        if (data.unLikesYn) {
            // $('#board-unlike').css('background-color', '#d3d3d3');
            // $('#board-unlike-img').css('background-color', '#d3d3d3');
            $('#board-unlike-img').css('color', '#FFDF65');
            $('#board-like-img').css('color', '#d3d3d3');

        } else {
            // $('#board-unlike').css('background-color', 'white');
            // $('#board-unlike-img').css('background-color', 'white');
            $('#board-unlike-img').css('color', '#d3d3d3');
        }
    })
}

var setBookMark = function () {
    const url = '/api/book-mark/board/set.api';
    console.log(BOARD_ID);
    const param = {
        tableId: BOARD_ID
    };
    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        console.log(data);
        // 원하는 색깔을 넣어주세요.
        if (data.bookMarkYn) {
            // $('#board-bookmark').css('background-color', 'black');
            $('#board-bookmark-img').css('color', 'black');
        } else {
            // $('#board-bookmark').css('background-color', 'white');
            $('#board-bookmark-img').css('color', 'limegreen');
        }
    })
}

var getGenre = function () {

    let queryString = location.search;
    if (queryString != undefined && queryString != '') {
        queryString = queryString.replaceAll('?', '');
        queryString = queryString.split("&");
        for (let i = 0; i < queryString.length; i++) {
            if ((queryString[i].indexOf('subId') + queryString[i].indexOf('=')) > 1) {
                SEARCH_GENRE = queryString[i].split('=')[1];
                break;
            }
        }
    }
}


// 페이지 이동 이벤트
var onClickPageIndex = function (pageIndex) {
    getCommentList(pageIndex);
}

// 댓글 작성 이벤트
var setComment = function (commentId, id) {
    const url = '/api/comment/board/set.api';

    id = id || 0;

    let contents = '';
    if (commentId < 1 && id < 1) {
        contents = $('#comment_contents').val() || '';
    } else if (id > 0) {
        contents = $('#comment_contents_' + id).val() || '';
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
        id: id,
        tableId: BOARD_ID,
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

// 댓글 목록 조회
var getCommentList = function (pageIndex) {
    const url = '/api/comment/board/gets.api';

    const param = {
        tableId: BOARD_ID,
        pageIndex: pageIndex
    };

    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        drawingCommentList(data.list, data.totalCount, pageIndex);
    })
}

// 대댓글 조회
var getReCommentList = function (commentId) {
    const url = '/api/comment/board/re/gets.api';
    const $rePageIndex = $('#pageIndex_' + commentId);
    const rePageIndex = parseInt($rePageIndex.val()) + 1 || 0;

    const param = {
        tableId: BOARD_ID,
        searchCommentId: commentId,
        pageIndex: rePageIndex
    };

    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        drawingReCommentList(commentId, data.list, data.totalCount, rePageIndex);
    })
}

// 단건 조회
var getSetComment = function (id) {
    const url = '/api/comment/board/set/get.api';

    const param = {
        id: id
    };

    API_CALL.post(url, param, function (result, message, data) {
        if (!result) {
            alert(message);
            return false;
        }
        changeInput(data);
    })
}

// 수정 화면 그려주기
var changeInput = function (comment) {
    const $div = $('#board_comment_' + comment.id);
    $div.empty();

    let html = '';
    html += '<textarea style="height: 15%; width: 100%" class="rounded" id="comment_contents_' + comment.id + '" placeholder="댓글을 입력해주세요.">' + comment.contents + '</textarea>';
    html += '<input type="hidden" id="comment_hidden_' + comment.id + '" value="' + comment.contents + '"/>';
    html += '<button type="button" style="min-width: 50px" class="btn btn-primary" id="comment_set_' + comment.id + '" onclick="onClickCommentSet(' + comment.id + ')">저장</button>'
    html += '<button type="button" style="min-width: 50px" class="btn btn-primary" id="comment_set_' + comment.id + '" onclick="onClickCancel(' + comment.id + ')">취소</button>'
    $div.append(html);
}


// 수정하기
var onClickCommentSet = function (id) {
    setComment(0, id);
}

// 수정 활성화
var onClickCommentSetDate = function (id) {
    const $btnSet = $('#btn_set_' + id);

    $btnSet.hide();
    getSetComment(id);
}

// 수정 취소
var onClickCancel = function (id) {
    const $div = $('#board_comment_' + id);
    const $hiddenInput = $('#comment_hidden_' + id);
    const $btnSet = $('#btn_set_' + id);

    const contents = $hiddenInput.val();

    $div.empty();
    let html = contents.replaceAll("\n", "<br/>");
    $div.append(html);

    $btnSet.show();
}

// 댓글 리스트 그려주기
var drawingCommentList = function (list, totalCount, pageIndex) {
    const $comments = $('#comment_list');

    $comments.empty();

    let html = '';

    list.forEach(function (comment) {
        html += '<div style="margin: 0px 0px;">';
        html += '   <div style="display: flex; justify-content: space-between"><b>' + comment.regNickname + '</b><span>' + comment.regDtText + '</span></div>';
        html += '   <div style="margin: 5px 0px" id="board_comment_' + comment.id + '">' + comment.contents.replaceAll("\n", "<br/>") + '</div>';
        html += '   <button type="button" style="font-size: 13px;" class="btn btn-primary"id="' + comment.id + '" onclick="onClickShowReCommentWrite()">' + '답글' + '</button>';
        if (comment.ownerYn) {
            html += '   <button type="button" style="font-size: 13px;" class="btn btn-primary" id="btn_set_' + comment.id + '" onclick="onClickCommentSetDate(' + comment.id + ')">' + '수정' + '</button>';
        }
        html += '   <div class="none" id="recomment_' + comment.id + '">';
        html += '       <div className="col-md-10">';
        html += '           <textarea style="height: 100%; width: 100%" class="rounded" id="comment_contents_' + comment.id + '" placeholder="댓글을 입력해주세요."></textarea>';
        html += '       </div>';
        html += '       <div className="col-md-2">';
        html += '           <button type="button" class="btn btn-primary" style="min-width: 50px; height: 100%;" id="comment_set_' + comment.id + '" onclick="onClickReCommentSet(' + comment.id + ')">등록</button>';
        html += '       </div>';
        html += '   </div>';
        if (comment.childCommentCount > 0) {
            html += '   <div>';
            html += '       <input type="hidden" id="re_comment_yn_' + comment.id + '" value="N" />';
            html += '       <button type="button" style="font-size: 13px; margin: 5px 0; background: transparent; border: none" onclick="onClickShowReComment(' + comment.id + ')">' + '댓글이 ' + comment.childCommentCount + '개 존재합니다.' + '</button>'
            html += '       <input type="hidden" id="pageIndex_' + comment.id + '" value="0"/>';
            html += '       <div id="re_comment_' + comment.id +'"></div>';
            html += '   </div>'
        }

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
        html += '   <div style="margin: 5px 0px" id="board_comment_' + comment.id + '">' + comment.contents.replaceAll("\n", "<br/>") + '</div>';
        if (comment.ownerYn) {
            html += '   <button type="button" style="font-size: 13px;" id="btn_set_' + comment.id + '" class="btn btn-primary"  onclick="onClickCommentSetDate(' + comment.id + ')">' + '수정' + '</button>';
        }
        html += '</div>';
    })

    $reComments.append(html);
}

// 대댓글 리스트 이벤트
var onClickShowReComment = function (commentId) {
    const $reCommentYn = $('#re_comment_yn_' + commentId);
    const $reComment = $('#re_comment_' + commentId);

    console.log($reCommentYn.val())
    console.log($reComment.val())

    if ($reCommentYn.val() === 'Y') {
        $reCommentYn.val('N');
        $reComment.empty();
    } else if ($reCommentYn.val() === 'N') {
        $reCommentYn.val('Y');
        getReCommentList(commentId);
    }
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

    if ($recommentId.hasClass('row') && !$recommentId.hasClass('none')) {
        $recommentId.removeClass('row');
        $recommentId.addClass('none');
    } else if (!$recommentId.hasClass('row') && $recommentId.hasClass('none')) {
        $recommentId.addClass('row');
        $recommentId.removeClass('none');
    } else {
        $recommentId.removeClass();
    }
}

// 댓글 작성 이벤트
var onClickReCommentSet = function (commentId) {
    setComment(commentId);
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
    getGenre();
    $('#moreList').on('click', function () {
        PAGE_INDEX += 1;
        if ((PAGE_INDEX - 1) * PAGE_SIZE > TOTAL_COUNT) {
            alert('더 이상 데이터가 없습니다.');
            return false;
        }
        getList();
    })
    //원하는 html id와 axios 함수 연결
    $('#board-like').on('click', function () {
        setLikes();
    })
    $('#board-unlike').on('click', function () {
        setUnlikes();
    })
    $('#board-bookmark').on('click', function () {
        setBookMark();
    })


    getCommentList(1);

    $('#comment_add').on('click', function () {
        setComment(0);
    })

    $('#not_login_contents').on('click', function () {
        notLoginContents();
    })
});
