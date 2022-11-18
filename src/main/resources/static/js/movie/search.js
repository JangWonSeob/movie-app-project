let PAGE_INDEX = 1;
let PAGE_SIZE = 8;

let LIST = [];
let TOTAL_COUNT = 0;

var onClickSearch = function () {
    getList(false)
}

var changeGenre = function (allYn) {

    let chk_val = [];

    $('input:checkbox[name=searchGenre]').each(function (index) {
        if (allYn) {
            $("input:checkbox[name='searchGenre']").prop("checked", false);
        } else {
            if($(this).is(":checked")==true){
                if ($(this).val() !== '') {
                    chk_val.push($(this).val());
                }
            }
        }
    });

    const result = chk_val.join(',');

    if (result === '') {
        $("input:checkbox[id='genre_all']").prop("checked", true);
    } else {
        $("input:checkbox[id='genre_all']").prop("checked", false);
    }

    return result;
}

var changeProvider = function (allYn) {

    let chk_val = [];

    $('input:checkbox[name=searchProvider]').each(function (index) {
        if (allYn) {
            $("input:checkbox[name=searchProvider]").prop("checked", false);
        } else {
            if($(this).is(":checked")==true){
                if ($(this).val() !== '') {
                    chk_val.push($(this).val());
                }
            }
        }
    });

    const result = chk_val.join(',');

    if (result === '') {
        $("input:checkbox[id='provider_all']").prop("checked", true);
    } else {
        $("input:checkbox[id='provider_all']").prop("checked", false);
    }

    return result;
}

var getList = function(moreYn) {
    const url = '/api/movie/search.api';

    if (!moreYn) {
        PAGE_INDEX = 1;
    }

    const param = {
        pageIndex: PAGE_INDEX,
        pageSize: PAGE_SIZE,
        searchGenreList: changeGenre(),
        searchProvider: changeProvider(),
        searchValue: $('#searchValue').val()
    };

    API_CALL.post(url, param, function (result, message, data) {
        if(!result) {
            alert(message);
            return false;
        }
        let movieList = data.list || [];
        TOTAL_COUNT = data.totalCount || 0;

        if (!moreYn) {
            $('#movieList').empty();
        }

        for (let i = 0; i < movieList.length; i++) {
            let append = '';
            append += '<div class="col-lg-3 mb-4">';
            append += '<a class="text-center text-decoration-none" href=" /movie/detail/' + movieList[i].id + ' ">';
            append += '<div class="card">';
            append += '<img src=" ' + movieList[i].fullPosterPath +  ' " class="card-img-top" alt="...">';
            append += '</div>';
            append += '<div style="color: black;">' + movieList[i].title + '</div>';
            append += '</a>';
            append += '</div>';

            $('#movieList').append(append);
        }
    })
}

$(function(){
    getList();

    $('#moreList').on('click', function () {
        PAGE_INDEX += 1;

        if ((PAGE_INDEX - 1) * PAGE_SIZE > TOTAL_COUNT) {
            alert('더 이상 데이터가 없습니다.');
            return false;
        }

        getList(true);
    })
    $("input:checkbox[id='genre_all']").prop("checked", true);
    $("input:checkbox[id='provider_all']").prop("checked", true);


    $('input:checkbox[name=searchGenre]').change(function (e) {
        console.log(e.target.value);
        if (e.target.value === '') {
            changeGenre(true);
        } else {
            changeGenre(false);
        }
    });

    $('input:checkbox[name=searchProvider]').change(function (e) {
        console.log(e.target.value);
        if (e.target.value === '') {
            changeProvider(true);
        } else {
            changeProvider(false);
        }
    });
});