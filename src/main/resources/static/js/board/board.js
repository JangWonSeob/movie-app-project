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
            $('#board-bookmark-img').css('color', 'limegreen');
        } else {
            // $('#board-bookmark').css('background-color', 'white');
            $('#board-bookmark-img').css('color', 'black');
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
});
