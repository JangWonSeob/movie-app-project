package com.website.movie.web.controller;


import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.dto.MovieDto;
import com.website.movie.biz.dto.TvDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.service.CodeService;
import com.website.movie.biz.service.MovieService;
import com.website.movie.biz.service.TvService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TvController {

    private final TvService tvService;
    private final CodeService codeService;

    // TV 목록 페이지
    @GetMapping("/tv/main")
    public String main(Model model) {

        model.addAttribute("codeList", tvService.main());

        return "tv/main";
    }

    // TV 목록 페이지
    @GetMapping("/tv/list")
    public String list(Model model,CodeDto parameter) {

        if(parameter.getSubId() == null) {
            parameter.setSubId("10759");
        }

        CodeDto codeDto = codeService.getBySubId(parameter);

        if(codeDto != null) {
            // 값이 없으면 액션 & 어드벤처로 세팅
            parameter.setSubId("10759");
            codeDto = codeService.getBySubId(parameter);
        }

        model.addAttribute("code", codeDto);

        return "tv/list";
    }

    // TV 목록 페이지
    @GetMapping("/tv/detail/{id}")
    public String detail(@AuthenticationPrincipal UserDto user, Model model, TvDto parameter) {

        TvDto result = tvService.get(parameter);

        if (result == null) {
            return "redirect:/error";
        }

        model.addAttribute("tv", result);
        model.addAttribute("loginYn", user != null);

        return "tv/detail";
    }

    @GetMapping("/tv/search")
    public String search(Model model,CodeDto parameter) {

        if(parameter.getSubId() == null) {
            parameter.setSubId("10759");
        }

        CodeDto codeDto = codeService.getBySubId(parameter);

        if(codeDto != null) {
            // 값이 없으면 어드벤처로 세팅
            parameter.setSubId("10759");
            codeDto = codeService.getBySubId(parameter);
        }

        model.addAttribute("code", codeDto);

        return "tv/search";
    }

}