package com.website.movie.web.controller;

import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String denied() {
        return "denied";
    }

    // @AuthenticationPrincipal은 평소에는 null만 나오지만
    // 로그인을 하면 userDto의 정보들이 나옵니다.
//    @GetMapping("/user/authPrincipal")
//    public String authPrincipal(@AuthenticationPrincipal UserDto user) {
//
//        System.out.println("@Authen 사용 user 정보"+ user);
//        System.out.println("@Authen 사용 user email"+ user.getEmail() + user.getId());
//        return "index";
//    }
    ///////////////////////// 퍼블리싱 연결용 컨트롤러

    @GetMapping("/index")
    public String index() {
        return "index";
    }

//    @GetMapping("/user/login")
//    public String login() {
//        return "/user/login";
//    }
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
    public String pwFindResetPost(UserDto user) {

        System.out.println("user=");
        System.out.println(user);

        userService.updatePassword(user);


        return "redirect:/user/login";
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

    @GetMapping("/user/pwFindReset")
    public String pwFindReset() {
        return "/user/pwFindReset";
    }


    @GetMapping("/user/signUp")
    public String signUp() {
        return "/user/signUp";
    }


}