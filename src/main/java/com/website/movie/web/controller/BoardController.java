package com.website.movie.web.controller;


import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.service.BoardService;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

//    @GetMapping("/board/boardUpdate")
//    public String boardUpdate(@RequestParam("id") int id) {
//        return "/board/boardUpdate";
//    }

    @GetMapping("/board/boardRegist")
    public String boardRegistGet(@AuthenticationPrincipal UserDto user, Model model) {

        System.out.println("user 정보"+ user);
        System.out.println("user 닉네임"+ user.getNickname());

        model.addAttribute("loginUserId",user.getId());
        model.addAttribute("nickname",user.getNickname());

        return "/board/boardRegist";
    }
    @PostMapping("/board/boardRegist")
    public String boardRegistPost(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        String title = request.getParameter("title");
        String writer = request.getParameter("writer");
        String loginUserId = request.getParameter("loginUserId");
        String category = request.getParameter("category");
        String contents = request.getParameter("contents");

        System.out.println("title"+title);

        System.out.println("loginUserId"+loginUserId);

        BoardDto board = new BoardDto();
        board.setTitle(title);
        board.setLoginUserId(Integer.parseInt(loginUserId));
        board.setCategory(category);
        board.setContents(contents);

        System.out.println("게시판 정보" + board);
        boardService.set(board);
        return "redirect:/board/boardList";
    }

    @GetMapping("/board/boardContents")
    public String boardContent(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        System.out.println("bdId값  "+request.getParameter("bdId"));
        int bdId = Integer.parseInt(request.getParameter("bdId"));
        BoardDto parameter = new BoardDto();

        parameter.setId(bdId);
        parameter.setCategory("자유");

        System.out.println("\n\n parameter정보 : " + parameter);
        BoardDto boardOne = boardService.get(parameter);
        System.out.println("\n\n boardOne정보 : " + boardOne);
        int write_id = boardOne.getRegId();
        UserDto userIdDto = new UserDto();
        userIdDto.setId(write_id);
        UserDto userOne = new UserDto();
        userOne = userService.get(userIdDto);

        String writer_nickname = userOne.getNickname();

        System.out.println("writer_nickname :" + writer_nickname);
        model.addAttribute("boardOne", boardOne);
        model.addAttribute("writer_nickname", writer_nickname);
//        http://localhost:8080/boardContents?bdId=11
        return "/board/boardContents";
    }
    @GetMapping("/board/boardList")
    public String boardList(Model model) {
        BoardDto parameter = new BoardDto();
        parameter.setCategory("자유");
        parameter.setStartIndex(0);
        parameter.setPageSize(12);
        List<BoardDto> list = boardService.gets(parameter);
        System.out.println("list :"+list);
        model.addAttribute("boardList",boardService.gets(parameter));


        return "/board/boardList";
    }

    @GetMapping("/board/communityList")
    public String communityList() {
        return "/board/communityList";
    }

    @GetMapping("/board/notice")
    public String notice() {
        return "/board/notice";
    }

    @GetMapping("/board/noticeList")
    public String noticeList() {
        return "/board/noticeList";
    }

}