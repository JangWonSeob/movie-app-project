package com.website.movie.web.controller;


import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.dto.MovieDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.service.CodeService;
import com.website.movie.biz.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

        if(parameter.getSubId() == null) {
            parameter.setSubId("12");
        }

        CodeDto codeDto = codeService.getBySubId(parameter);

        if(codeDto != null) {
            // 값이 없으면 어드벤처로 세팅
            parameter.setSubId("12");
            codeDto = codeService.getBySubId(parameter);
        }

        model.addAttribute("code", codeDto);

        return "movie/list";
    }

    // 영화 목록 페이지
    @GetMapping("/movie/detail/{id}")
    public String detail(@AuthenticationPrincipal UserDto user, Model model, MovieDto parameter) {

        if (user != null) {
            parameter.setLoginUserId(user.getId());
        }
        MovieDto result = movieService.get(parameter);

        if (result == null) {
            return "redirect:/error";
        }

        model.addAttribute("movie", result);
        model.addAttribute("loginYn", user != null);

        return "movie/detail";
    }

    @GetMapping("/movie/search")
    public String search(Model model,CodeDto parameter) {

        if(parameter.getSubId() == null) {
            parameter.setSubId("12");
        }

        CodeDto codeDto = codeService.getBySubId(parameter);

        if(codeDto == null) {
            // 값이 없으면 어드벤처로 세팅
            parameter.setSubId("12");
            codeDto = codeService.getBySubId(parameter);
        }

        model.addAttribute("code", codeDto);

        return "movie/search";
    }

}