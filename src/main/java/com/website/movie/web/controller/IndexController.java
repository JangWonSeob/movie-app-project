package com.website.movie.web.controller;

import com.website.movie.biz.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MovieService movieService;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("movieList",movieService.index());

        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

}
