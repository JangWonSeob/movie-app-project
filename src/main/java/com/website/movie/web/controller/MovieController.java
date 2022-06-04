package com.website.movie.web.controller;


import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.service.CodeService;
import com.website.movie.biz.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final CodeService codeService;

    // 영화 목록 페이지
    @GetMapping("/movie/main")
    public String main(Model model) {

        model.addAttribute("codeList", movieService.main());

        return "movie/main";
    }

    // 영화 목록 페이지
    @GetMapping("/movie/list")
    public String list(Model model,CodeDto parameter) {

        if(parameter.getId() == null) {
            parameter.setId("12");
        }

        CodeDto codeDto = codeService.get(parameter);

        if(codeDto != null) {
            // 값이 없으면 어드벤처로 세팅
            parameter.setId("12");
            codeDto = codeService.get(parameter);
        }

        model.addAttribute("code", codeDto);

        return "movie/list";
    }


}