const MOVIE_ID = location.pathname.split('/')[3];

var setBookMark = function() {
    const url = '/api/book-mark/movie/set.api';

    console.log(MOVIE_ID);


    const param = {
        tableId: MOVIE_ID
    };

    API_CALL.post(url, param, function (result, message, data) {
        if(!result) {
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

var setComment = function(commentId) {
    const url = '/api/comment/movie/set.api';

    const contents = $('#comment_contents').val() || '';
    if (contents.length < 1) {
        alert('댓글 내용을 입력해주세요.');
        return false;
    }

    console.log(MOVIE_ID);
    console.log(contents);

    const param = {
        tableId: MOVIE_ID,
        commentId: commentId,
        contents: contents
    };

    API_CALL.post(url, param, function (result, message, data) {
        if(!result) {
            alert(message);
            return false;
        }
        alert('등록되었습니다.');
        location.reload();
    })
}

var drawIngPager = function () {

    PAGER.drawingPager(50, 1);

}

$(function(){
    drawIngPager();

    $('#movie-bookmark').on('click', function () {
        setBookMark();
    })

    $('#comment_set').on('click', function () {
        setComment(0);
    })
});