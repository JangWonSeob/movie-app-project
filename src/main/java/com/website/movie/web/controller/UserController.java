package com.website.movie.web.controller;

import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.input.UserInputModel;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // SecurityConfig에서 가져옴
//    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @GetMapping("/user/gets.api")
//    public List<UserDto> gets() {
//        return userService.gets();
//    }

    @GetMapping("/user")
    public String user() {

        return "user";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }



    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(UserInputModel model) {
        System.out.println(model);
//        model.
        //Security 이용 암호화 코드
        String rawPassword = model.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        model.setPassword(encPassword);

        return "redirect:/loginForm";
    }

}
