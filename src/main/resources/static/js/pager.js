if (typeof (movie) == 'undefined') { movie = {}; }
if (typeof (movie.app) == 'undefined') { movie.app = {}; }
if (typeof (movie.app.project) == 'undefined') { movie.app.project = {}; }
if (typeof (movie.app.project.pager) == 'undefined') { movie.app.project.pager = {}; }

//     <ul className="pagination justify-content-center">
//         <li className="page-item"><a className="page-link" href="javascript:;">이전</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">1</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">2</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">3</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">4</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">5</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">6</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">7</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">8</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">9</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">10</a></li>
//         <li className="page-item"><a className="page-link" href="javascript:;">다음</a></li>
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


        const _pager = $('#pager');

        _pager.empty();

        let _html = '';
        _html += '<ul class="pagination justify-content-center">';
        _html += '<li class="page-item"><a class="page-link" href="javascript:;" onclick="onClickPageIndex(' + _prevPageIndex + ')">이전</a></li>';

        _list.forEach(function (i) {
            if (_pageIndex === i) {
                _html += '<li class="page-item active"><a class="page-link" href="javascript:;" onclick="onClickPageIndex(' + i + ')">' + i + '</a></li>';
            } else {
                _html += '<li class="page-item"><a class="page-link" href="javascript:;" onclick="onClickPageIndex(' + i + ')">' + i + '</a></li>';
            }
        })

        _html += '<li class="page-item"><a class="page-link" href="javascript:;" onclick="onClickPageIndex(' + _nextPageIndex + ')">다음</a></li>';
        _html += '</ul>';

        // console.log(_html);

        _pager.append(_html);

    }

    _context_.drawingPager = function(totalCount, pageIndex, pageSize) {
        pager(totalCount, pageIndex, pageSize);
    };


})(movie.app.project.pager);


if (typeof (API_CALL) == 'undefined') { API_CALL = {}; }
PAGER = movie.app.project.pager;