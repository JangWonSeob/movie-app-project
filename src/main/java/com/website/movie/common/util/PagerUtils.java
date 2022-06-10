package com.website.movie.common.util;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * PagerUtils 클래스
 */
@Data
@Component
public class PagerUtils {

    public static final int DEFAULT_PAGE_SIZE = 10;
    final public static int DEFAULT_PAGE_BLOCK = 10;

    private boolean isPrevPage;         //이전페이지 존재여부
    private boolean isNextPage;         //다음페이지 존재여부
    private int nowPage;                //현재페이지
    private int rowTotal;               //총 게시물수
    private int blockList;              //한페이지에 표시될 리스트수
    private int blockPage;              //한 블럭에 표시될 페이지수
    private int blockSize;
    private int totalPage;              //전체페이지수
    private int startPage;              //시작페이지
    private int endPage;                //마지막 페이지
    private int startRow;               //데이타베이스의 쿼리에서 사용할 시작값
    private int endRow;                 //데이타베이스의 쿼리에서 사용할 종료값
    private int startNum;               //리스트 표기시 처음 시작 숫자

    /*
     * 페이지를 계산하는 생성자
     * nowPage : 현재페이지
     * rowTotal : 총게시물수
     * blockList : 한페이지에 디스플레이될 게시물수
     * blockPage: 한 블럭에 표시될 페이지수
     */


    public PagerUtils() {
        initialize(0, 0, 0, PagerUtils.DEFAULT_PAGE_BLOCK);
    }

    public PagerUtils(final int nowPage, final int rowTotal, final int blockList, final int blockPage) {
        initialize(nowPage, rowTotal, blockList, blockPage);
    }


    public PagerUtils(final int page, final int size, final int totalCount) {
        initialize(page, totalCount, size, PagerUtils.DEFAULT_PAGE_BLOCK);
    }

    public PagerUtils(final int page, final int totalCount) {
        initialize(page, totalCount, PagerUtils.DEFAULT_PAGE_SIZE, PagerUtils.DEFAULT_PAGE_BLOCK);
    }

    private void initialize(int nowPage, final int rowTotal, final int blockList, final int blockPage) {

        isPrevPage = false;
        isNextPage = false;

        int currentPage = nowPage;

        this.totalPage = (int) Math.ceil((double) rowTotal / (double) blockList);

        if (currentPage > this.totalPage) currentPage = this.totalPage;

        if (currentPage < 1) currentPage = 1;

        this.startRow = (currentPage - 1) * blockList; // 30
        this.endRow = this.startRow + blockList - 1; // 39

        this.startPage = ((currentPage - 1) / blockPage) * blockPage + 1; //
        this.endPage = this.startPage + blockPage - 1;

        this.startNum = rowTotal - (blockList * (currentPage - 1));

        if (this.endPage > this.totalPage) {
            this.endPage = totalPage;
        }

        if (this.startPage > 1) {
            this.isPrevPage = true;
        }

        if (this.endPage < this.totalPage) {
            this.isNextPage = true;
        }

        this.nowPage = currentPage;
        this.rowTotal = rowTotal;
        this.blockList = blockList;
        this.blockPage = blockPage;
    }

    public String printFrontPager(final String parameter) {

        if (rowTotal < 1) {
            return "";
        }

        final StringBuffer sb = new StringBuffer();

        sb.append("<nav aria-label='Page navigation'>");
        sb.append("<ul class='pagination justify-content-center'>");

        sb.append(String.format("<li class='page-item'><a class='page-link' href='?pageIndex=1%s'>이전</a></li>", parameter));

//        if (isPrevPage) {
//            sb.append(String.format("<li class='page-item'><a class='page-link' href='/board/list?pageIndex=%d%s'>이전</a></li>", nowPage - 1, parameter));
//        } else {
//            sb.append(String.format("<li class='page-item'><a class='page-link' href='#'>이전</a></li>"));
//        }

        for (int i = startPage; i <= endPage; i++) {
            if (i > startPage) {
                sb.append(" ");
            }

            if (nowPage == i) {
                sb.append(String.format("<li class='page-item active'><a class='page-link' href='#'>%d</a></li>", i));
            } else {
                sb.append(String.format("<li class='page-item'><a class='page-link' href='?pageIndex=%d%s'>%d</a></li>", i, parameter, i));
            }
        }

//        if (isNextPage) {
//            sb.append(String.format("<li class='page-item'><a class='page-link' href='?pageIndex=%d%s'>다음</a></li>", endPage + 1, parameter));
//        } else {
//            sb.append(String.format("<li class='page-item'><a class='page-link' href='#'>다음</a></li>"));
//        }

        sb.append(String.format("<li class='page-item'><a class='page-link' href='?pageIndex=%d%s'>다음</a></li>", totalPage, parameter));

        sb.append("</ul>");
        sb.append("</nav>");

        return sb.toString();
    }

}