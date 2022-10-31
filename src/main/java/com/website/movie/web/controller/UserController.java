package com.website.movie.web.controller;

import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/user/signUp")
    public String signUp() {
        return "/user/signUp";
    }
    
    // 회원가입 처리
    @PostMapping("/user/signUp")
    public String signUpPost(UserDto user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        // 여러권한 추가 가능
        //		authorities.add(new SimpleGrantedAuthority("ADMIN"));
        authorities.add(new SimpleGrantedAuthority("USER"));
        user.setAuthorities(authorities);
        userService.createUser(user);

        return "redirect:/user/login";
    }

    // 마이페이지
    @GetMapping("/user/user")
    public String user() {
        return "/user/auth/user";
    }

    // 로그아웃 처리
    @GetMapping("/user/logout")
    public String logout() {
        return "index";
    }

    // 로그아웃 결과 페이지
//    @GetMapping("/user/logout/result")
//    public String logoutResult() {
//        return "index";
//    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // 로그인페이지
    @GetMapping("/user/login")
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/login";
    }
    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String loginResult() {
        return "index";
    }

    @GetMapping("/user/loginAlert")
    public String loginAlert() {
        return "/user/loginAlert";
    }

    @GetMapping("/user/pwFind")
    public String pwFind() {
        return "/user/pwFind";
    }

    @PostMapping("/user/pwFind")
    public String pwFindPost(UserDto user) {

        System.out.println("user=");
        System.out.println(user);

        userService.updatePassword(user);
        return "redirect:/user/login";
    }

    //user -> 비밀번호변경 화면에서 받은 유저
    @GetMapping("/user/myPwReset")
    public String pwFindReset(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/user/myPwReset";
    }

    @PostMapping("/user/myPwReset")
    public String pwFindResetPost(HttpServletResponse response, @RequestParam String passwordCurrent, UserDto user, @AuthenticationPrincipal UserDto nowuser) throws IOException {
        response.setContentType("text/html; charset=euc-kr");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(passwordCurrent, nowuser.getPassword()))
        {
            nowuser.setPassword(user.getPassword());
            System.out.println(nowuser);
            userService.updatePassword(nowuser);
            return "redirect:/board/mypage";
        }
        else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('현재 비밀번호가 일치하지 않습니다.');</script>");
            out.flush();
        }
        return "/user/myPwReset";
    }

//    @PostMapping("/user/pwFind")
//    public String pwFindResetPost(HttpServletRequest request, UserDto user) throws UnsupportedEncodingException {
//        request.setCharacterEncoding("utf-8");
//
//        request.getParameter("email");
//        request.getParameter("password");
//
//
//        userService.updateUser(user);
//
//        System.out.println("user=");
//        System.out.println(user);
//
//        return "redirect:/user/login";
//    }




}