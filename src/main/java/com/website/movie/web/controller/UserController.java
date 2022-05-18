package com.website.movie.web.controller;

import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    @GetMapping("/user/gets.api")
//    public List<UserDto> gets() {
//        return userService.gets();
//    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }
    @GetMapping("/emailCheckSend")
    public String emailCheckSend() {

        return "emailCheckSend";
    }

    @GetMapping("/user/signup")
    public String signup()
    {
        return "signup";
    }


    // 회원가입 처리
    @PostMapping("/user/signup")
    public String signupPost(UserDto user) {
        System.out.println("\n 변환하기전 \n" + user);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("USER"));
        user.setAuthorities(authorities);
        user.setCertified(certified_key());  //회원가입을 하면 랜덤한 키값을 받는다 나중에 이 값을 URL에 담은채로 특정URL에 접속해야지만 진짜 가입상태가 된다.
        System.out.println("\n 변환이후 \n" + user);
        userService.createUser(user);
        return "redirect:/user/loginPage";
    }
    private String certified_key() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;

        do {
            num = random.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                sb.append((char) num);
            } else {
                continue;
            }

        } while (sb.length() < 10);
        return sb.toString();
    }
    // 로그인 페이지
    @GetMapping("/user/loginPage")
    public String login() {
        return "login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String loginResult() {
        return "loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String logout() {
        return "logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String denied() {
        return "denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String myInfo() {
        return "myinfo";
    }

    // 어드민 페이지
    @GetMapping("/user/admin")
    public String admin() {
        return "admin";
    }
}
