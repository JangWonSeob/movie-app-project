package com.website.movie.web.controller;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.service.BoardService;
import com.website.movie.biz.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MovieService movieService;
    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model) {

        BoardDto parameter = BoardDto.builder().build();

        model.addAttribute("boardList",boardService.index(parameter));
        model.addAttribute("movieList",movieService.index());
        model.addAttribute("tvList",movieService.index2());
        parameter.setSearchCategory("영화리뷰");
        model.addAttribute("movieBoardList",boardService.index(parameter));
        parameter.setSearchCategory("자유");
        model.addAttribute("freedomBoardList",boardService.index(parameter));

        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

}
