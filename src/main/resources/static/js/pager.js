if (typeof (movie) == 'undefined') { movie = {}; }
if (typeof (movie.app) == 'undefined') { movie.app = {}; }
if (typeof (movie.app.project) == 'undefined') { movie.app.project = {}; }
if (typeof (movie.app.project.pager) == 'undefined') { movie.app.project.pager = {}; }

//     <ul className="pagination justify-content-center">
//         <li className="page-item"><a className="page-link" href="#">이전</a></li>
//         <li className="page-item"><a className="page-link" href="#">1</a></li>
//         <li className="page-item"><a className="page-link" href="#">2</a></li>
//         <li className="page-item"><a className="page-link" href="#">3</a></li>
//         <li className="page-item"><a className="page-link" href="#">4</a></li>
//         <li className="page-item"><a className="page-link" href="#">5</a></li>
//         <li className="page-item"><a className="page-link" href="#">6</a></li>
//         <li className="page-item"><a className="page-link" href="#">7</a></li>
//         <li className="page-item"><a className="page-link" href="#">8</a></li>
//         <li className="page-item"><a className="page-link" href="#">9</a></li>
//         <li className="page-item"><a className="page-link" href="#">10</a></li>
//         <li className="page-item"><a className="page-link" href="#">다음</a></li>
//     </ul>



(function (_context_) {
    const DEFAULT_PAGE_SIZE = 10;

    const pager = function (totalCount, pageIndex, pageSize) {
        totalCount = totalCount || 0;
        pageIndex = pageIndex || 1;
        pageSize = pageSize || DEFAULT_PAGE_SIZE;

        let _startPageIndex = 1;
        let _endPageIndex = 1;
        let _nextPageIndex = 1;
        let _prevPageIndex = 1;
        let _finalPageIndex = 1;
        let _pageBlockSize = 10;

        let _pageIndex = pageIndex;
        let _totalCount = totalCount;
        let _pageSize = pageSize;
        let _totalPage = Math.ceil(_totalCount / _pageSize);

        let _currentPageBlock = Math.floor((_pageIndex - 1) / _pageBlockSize + 1)            // 현재 블럭

        let _start = (_currentPageBlock - 1) * _pageBlockSize + 1;                              // 블럭 시작 페이지
        let _end = _start + _pageBlockSize - 1;                                                 // 블럭 마지막 페이지
        if (_end > _totalPage) _end = _totalPage;                                               // 블럭 마지막 페이지 재설정
        let _final = Math.floor((_totalCount + (_pageSize - 1)) / _pageSize);                // 마지막 페이지

        let _prevBlock = _start - _pageBlockSize < 0 ? 1 : _start - _pageBlockSize;
        let _nextBlock = (_end + 1) < _final ? _end + 1 : _final;

        _startPageIndex = _start;
        _endPageIndex = _end;
        _nextPageIndex = _nextBlock;
        _prevPageIndex = _prevBlock;
        _finalPageIndex = _final;

        let _list = [];
        for (let i = _startPageIndex; i <= _endPageIndex; i++) {
            _list.push(i);
        }


    // <div class="pager">
    //     <a @click="goPageIndex(mxPage.firstPageIndex)" :disabled="mxIsFirstPageIndex" href="javascript:;" class="btn_first"></a>
    //     <a @click="goPageIndex(mxPage.prevPageIndex)" :disabled="mxIsPrevPageIndex" href="javascript:;" class="btn_prev"></a>
    //     <a v-for="(x, index) in mxPageRange" :disabled="search.pageIndex == x" :key="index" v-text="x" @click="goPageIndex(x)" href="javascript:;" :class="{ 'on': mxPage.pageIndex == x}">1</a>
    //     <a @click="goPageIndex(mxPage.nextPageIndex)" :disabled="mxIsNextPageIndex" href="javascript:;" class="btn_next"></a>
    //     <a @click="goPageIndex(mxPage.finalPageIndex)" :disabled="mxIsFinalPageIndex" href="javascript:;" class="btn_last"></a>
    // </div>


        const _pager = $('#pager');

        _pager.empty();

        let _html = '';
        _html += '<ul className="pagination justify-content-center">';
        _html += '<li className="page-item"><a className="page-link" href="#">이전</a></li>';

        _list.forEach(function (i) {
            _html += '<li className="page-item"><a className="page-link" href="#">' + i + '</a></li>';
        })

        _html += '<li className="page-item"><a className="page-link" href="#">다음</a></li>';
        _html += '</ul>';

        console.log(_html);

        _pager.append(_html);

    }

    _context_.drawingPager = function(totalCount, pageIndex, pageSize) {
        pager(totalCount, pageIndex, pageSize);
    };


})(movie.app.project.pager);


if (typeof (API_CALL) == 'undefined') { API_CALL = {}; }
PAGER = movie.app.project.pager;