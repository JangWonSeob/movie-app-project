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

$(function(){

    $('#movie-bookmark').on('click', function () {
        setBookMark();
    })
});