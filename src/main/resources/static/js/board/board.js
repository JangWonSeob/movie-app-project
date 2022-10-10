let PAGE_INDEX = 1;
let PAGE_SIZE = 10;
let SEARCH_GENRE = '';

let LIST = [];
let TOTAL_COUNT = 0;

// BOARD_ID
// http://localhost:8080/board/detail/118
// split('/')[3] == 118
const BOARD_ID = location.pathname.split('/')[3];

var setLikes = function() {
    const url = '/api/likes/set.api';

    const param = {
        boardId: BOARD_ID,
    };

    console.log("boardId",BOARD_ID)

    API_CALL.post(url, param, function (result, message, data) {
        if(!result) {
            alert(message);
            return false;
        }
        console.log(data);
        // 원하는 색깔을 넣어주세요.
        if (data.likesYn) {
            $('#board-like').css('background-color', 'black');
            $('#board-like').css('color', 'white');
        } else {
            $('#board-like').css('background-color', 'white');
            $('#board-like').css('color', 'black');
        }
    })
}

var setUnlikes = function() {
    const url = '/api/unlikes/set.api';

    const param = {
        boardId: BOARD_ID
    };

    API_CALL.post(url, param, function (result, message, data) {
        if(!result) {
            alert(message);
            return false;
        }
        console.log(data);
        // 원하는 색깔을 넣어주세요.
        if (data.unLikesYn) {
            $('#board-bookmark').css('background-color', 'black');
            $('#board-bookmark').css('color', 'white');
        } else {
            $('#board-bookmark').css('background-color', 'white');
            $('#board-bookmark').css('color', 'black');
        }
    })
}


var setBookMark = function() {
    const url = '/api/book-mark/board/set.api';
    console.log(BOARD_ID);
    const param = {
        tableId: BOARD_ID
    };
    API_CALL.post(url, param, function (result, message, data) {
        if(!result) {
            alert(message);
            return false;
        }
        console.log(data);
        // 원하는 색깔을 넣어주세요.
        if (data.bookMarkYn) {
            $('#board-bookmark').css('background-color', 'black');
            $('#board-bookmark').css('color', 'white');
        } else {
            $('#board-bookmark').css('background-color', 'white');
            $('#board-bookmark').css('color', 'black');
        }
    })
}

var getGenre = function () {

    let queryString = location.search;

    if(queryString != undefined && queryString != '') {
        queryString = queryString.replaceAll('?', '');
        queryString = queryString.split("&");
        for (let i = 0; i < queryString.length; i++) {
            if ((queryString[i].indexOf('subId') + queryString[i].indexOf('='))  > 1) {
                SEARCH_GENRE = queryString[i].split('=')[1];
                break;
            }
        }
    }
}

// var getList = function() {
//     const url = '/api/movie/gets.api';
//     const param = {
//         pageIndex: PAGE_INDEX,
//         pageSize: PAGE_SIZE,
//         searchGenre: SEARCH_GENRE,
//     };
//
//     API_CALL.post(url, param, function (result, message, data) {
//         if(!result) {
//             alert(message);
//             return false;
//         }
//         let movieList = data.list || [];
//         TOTAL_COUNT = data.totalCount || 0;
//
//         for (let i = 0; i < movieList.length; i++) {
//
//             // <div className="col-lg-2">
//             //     <div className="card" style="width: 15rem;">
//             //         <!--                    <img src="test/test.png" class="card-img-top" alt="...">-->
//             //     </div>
//             //     <div>제목</div>
//             // </div>
//
//             // <div th:each="movie : ${code.movieList}" className="col-lg-2">
//             //     <a th:href="@{/movie/detail/{id}(id=${movie.id})}">
//             //         <div className="card" style="width: 15rem;">
//             //             <img th:src="${movie.fullPosterPath}" className="card-img-top" alt="...">
//             //         </div>
//             //         <div style="color: white" th:text="${movie.title}"></div>
//             //     </a>
//             // </div>
//
//             let append = '';
//             append += '<div class="col-lg-2">';
//             append += '<a href=" /movie/detail/' + movieList[i].id + ' ">';
//             append += '<div class="card" style="width: 15rem;">';
//             append += '<img src=" ' + movieList[i].fullPosterPath +  ' " class="card-img-top" alt="...">';
//             append += '</div>';
//             append += '<div>' + movieList[i].title + '</div>';
//             append += '</a>';
//             append += '</div>';
//
//             $('#movieList').append(append);
//         }
//     })
// }
$(function(){
    getGenre();
    // getList();

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

