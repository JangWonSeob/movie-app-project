package com.website.movie.web.controller;


import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.service.BoardService;
import com.website.movie.biz.service.UserService;
import com.website.movie.common.util.PagerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    public String getSearchParam(BoardDto parameter) {
        if (parameter == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        if (!StringUtils.isEmpty(parameter.getCategory())) {
            sb.append(String.format("&category=%s", parameter.getCategory()));
        }

        if (!StringUtils.isEmpty(parameter.getSearchType())) {
            sb.append(String.format("&searchType=%s", parameter.getSearchType()));
        }

        if (!StringUtils.isEmpty(parameter.getSearchValue())) {
            sb.append(String.format("&searchValue=%s", parameter.getSearchValue()));
        }

        return sb.toString();
    }

    @GetMapping("/board/write")
    public String boardWriteGet(@AuthenticationPrincipal UserDto user, Model model) {

        System.out.println("user 정보" + user);
        System.out.println("user 닉네임" + user.getNickname());

        model.addAttribute("loginUserId", user.getId());
        model.addAttribute("nickname", user.getNickname());

        return "board/boardWrite";
    }

    @PostMapping("/board/write")
    public String boardWritePost(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        int id;
        if (request.getParameter("id") == null) {
            id = 0;
        } else {
            id = Integer.parseInt(request.getParameter("id"));
        }
        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String loginUserId = request.getParameter("loginUserId");
        String category = request.getParameter("category");
        String contents = request.getParameter("contents");

        System.out.println("title" + title);

        System.out.println("loginUserId" + loginUserId);

        BoardDto board = new BoardDto();
        board.setId(id);
        board.setTitle(title);
        board.setLoginUserId(Integer.parseInt(loginUserId));
        board.setCategory(category);
        board.setContents(contents);
        board.setDisplayYn(true);

        System.out.println("게시판 정보" + board);
        boardService.set(board);
        return "redirect:board/list";
    }

    @GetMapping("/board/update/{id}")
    public String boardUpdate(@AuthenticationPrincipal UserDto user, Model model, BoardDto parameter) throws UnsupportedEncodingException {
        System.out.println(parameter);
        System.out.println("\n\n" + parameter.getId());

        model.addAttribute("loginUserId", user.getId());
        model.addAttribute("detail", boardService.get(parameter));

        return "board/boardUpdate";
    }

    @GetMapping("/board/delete/{id}")
    public String boardDelete(@AuthenticationPrincipal UserDto user, BoardDto parameter) throws UnsupportedEncodingException {
        BoardDto board = boardService.get(parameter);

        if (user.getId() == board.getRegId()) {
            board.setLoginUserId(user.getId());
            System.out.println("boardService delete");
            boardService.delete(board);
        }
        return "redirect:board/list";
    }

    @GetMapping("/board/detail/{id}")
    public String detail(@AuthenticationPrincipal UserDto user, Model model, BoardDto parameter) {
        int userId = 0;
        if (user != null) {
            userId = user.getId();
            parameter.setLoginUserId(userId);
        }
        BoardDto result = boardService.get(parameter);
        if (result == null) {
            return "redirect:/error";
        }

        System.out.println("result" + result);
        System.out.println("parameter" + parameter);

        boardService.viewCountUp(parameter);
        model.addAttribute("detail", result);
        model.addAttribute("writerId", result.getRegId());
        model.addAttribute("loginUserId", userId);

        model.addAttribute("loginYn", user != null);

        return "board/boardContents";
    }

    @GetMapping("/board/list")
    public String boardList(HttpServletRequest request, Model model, BoardDto parameter) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        String category = request.getParameter("category");
//        int startIndex = Integer.parseInt(request.getParameter("startIndex"));
        System.out.println("category 출력 : " + category);
        if (category == null) {
            category = "";
        }
        System.out.println("parameter 출력 : " + parameter);

        if (category.equals("영화리뷰")) {
            parameter.setSearchCategory("영화리뷰");
        } else if (category.equals("시사회")) {
            parameter.setSearchCategory("시사회");
        } else if (category.equals("토론")) {
            parameter.setSearchCategory("토론");
        } else if (category.equals("유머")) {
            parameter.setSearchCategory("유머");
        } else if (category.equals("극장맛집")) {
            parameter.setSearchCategory("극장맛집");
        } else if (category.equals("굿즈")) {
            parameter.setSearchCategory("굿즈");
        } else if (category.equals("극장볼거리")) {
            parameter.setSearchCategory("극장볼거리");
        } else {
            System.out.println("예외 카테고리로 넘어옴");
            parameter.setSearchCategory("자유");
        }
//        parameter.setStartIndex(startIndex);
        parameter.initPage();
        parameter.setSqlSelectType("FRONT");  // DISPLAY_YN 구별

        System.out.println("set처리이후 parameter 출력 : " + parameter);

        int totalCount = boardService.totalCount(parameter);
        List<BoardDto> list = boardService.gets(parameter, totalCount);

        System.out.println("list :" + list);
        model.addAttribute("boardTitle", parameter.getSearchCategory());
        model.addAttribute("boardList", list);
        model.addAttribute("totalCount", totalCount);
        final PagerUtils pagerUtils = new PagerUtils(parameter.getPageIndex(), parameter.getPageSize(), totalCount);
        this.getSearchParam(parameter);
        final String pager = pagerUtils.printFrontPager("&category=" + category);

        model.addAttribute("pager", pager);

        return "board/boardList";
    }

    @GetMapping("/board/notice")
    public String notice() {
        return "board/notice";
    }

    @GetMapping("/board/noticeList")
    public String noticeList() {
        return "board/noticeList";
    }

    @GetMapping("/board/mypage")
    public String mypage(@AuthenticationPrincipal UserDto user, Model model) {

        model.addAttribute("user", user);

        return "board/mypage";
    }

    @GetMapping("/board/myBoard")
    public String myBoard(@AuthenticationPrincipal UserDto user, HttpServletRequest request, Model model, BoardDto parameter) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        String category = request.getParameter("category");
//        int startIndex = Integer.parseInt(request.getParameter("startIndex"));

        parameter.setSearchCategory("자유");
        parameter.setLoginUserId(user.getId());

        parameter.initPage();
        parameter.setSqlSelectType("MY_BOARD_LIST");  // DISPLAY_YN 구별

        System.out.println("parameter 출력 : " + parameter);

        int totalCount = boardService.totalCount(parameter);
        List<BoardDto> list = boardService.gets(parameter, totalCount);

        System.out.println("list :" + list);
        model.addAttribute("boardTitle", parameter.getSearchCategory());
        model.addAttribute("boardList", list);
        model.addAttribute("totalCount", totalCount);
        final PagerUtils pagerUtils = new PagerUtils(parameter.getPageIndex(), parameter.getPageSize(), totalCount);
        this.getSearchParam(parameter);
        final String pager = pagerUtils.printFrontPager("&category=" + category);

        model.addAttribute("pager", pager);
        return "board/myBoard";
    }

    @GetMapping("/board/bookmarkList")
    public String bookmarkList(@AuthenticationPrincipal UserDto user, HttpServletRequest request, Model model, BoardDto parameter) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        String category = request.getParameter("category");

        parameter.setLoginUserId(user.getId());

        parameter.initPage();
        parameter.setSqlSelectType("MY_BOOKMARK_LIST");  // DISPLAY_YN 구별

        System.out.println("parameter 출력 : " + parameter);

        int totalCount = boardService.totalCount(parameter);
        List<BoardDto> list = boardService.gets(parameter, totalCount);

        System.out.println("list :" + list);
        model.addAttribute("boardTitle", parameter.getSearchCategory());
        model.addAttribute("boardList", list);
        model.addAttribute("totalCount", totalCount);
        // TODO: 페이징 처리
        final PagerUtils pagerUtils = new PagerUtils(parameter.getPageIndex(), parameter.getPageSize(), totalCount);
        this.getSearchParam(parameter);
        final String pager = pagerUtils.printFrontPager("&category=" + category);

        model.addAttribute("pager", pager);
        return "board/bookmarkList";
    }
}