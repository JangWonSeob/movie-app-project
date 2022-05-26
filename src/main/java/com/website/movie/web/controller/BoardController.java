package com.website.movie.web.controller;


import com.website.movie.biz.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //
    @GetMapping("boardUpdate")
    public String boardUpdate() {
        return "boardUpdate";
    }
    @GetMapping("boardRegist")
    public String boardRegist() {
        return "boardRegist";
    }
    @GetMapping("boardContent")
    public String boardContent() {
        return "boardContent";
    }
    @GetMapping("boardList")
    public String boardList() {
        return "boardList";
    }


}




