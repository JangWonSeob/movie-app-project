package com.website.movie.biz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {

    @GetMapping("/api/index")
    public String indexMovie(){
        System.out.println("/api/index");
        return "안녕하세요";
    }

    @GetMapping("/api/index2")
    public String index2(){
        System.out.println("/api/index2");
        return "안녕하세요";
    }

}
