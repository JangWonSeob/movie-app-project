package com.website.movie.web.controller;

import com.website.movie.biz.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final MovieService movieService;

    @GetMapping("/movieList")
    public String movieList(Model model) {

        model.addAttribute("movieList", movieService.main());

        return "movie/main";
    }
}
