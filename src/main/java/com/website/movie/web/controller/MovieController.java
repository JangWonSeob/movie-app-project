package com.website.movie.web.controller;


import com.website.movie.biz.dto.MovieDto;
import com.website.movie.biz.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    // 영화 목록 페이지
    @GetMapping("/movie/main")
    public String boardList(Model model) {

        MovieDto parameter = new MovieDto();
        // 액션
        parameter.setSearchGenre("28");
        model.addAttribute("actionList",movieService.main(parameter));
        // 로맨스
        parameter.setSearchGenre("10749");
        model.addAttribute("romanceList",movieService.main(parameter));
        // 코미디
        parameter.setSearchGenre("35");
        model.addAttribute("comedyList",movieService.main(parameter));
        // 판타지
        parameter.setSearchGenre("14");
        model.addAttribute("fantasyList",movieService.main(parameter));
        // 스릴러
        parameter.setSearchGenre("53");
        model.addAttribute("thrillerList",movieService.main(parameter));

        return "movie/main";
    }

}