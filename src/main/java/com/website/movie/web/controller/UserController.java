package com.website.movie.web.controller;

import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 처리
    @PostMapping("/user/signUp")
    public String signupPost(UserDto user) {
        System.out.println("\n 변환하기전 \n" + user);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("USER"));
        user.setAuthorities(authorities);
        //회원가입을 하면 랜덤한 키값을 받는다 나중에 이 값을 URL에 담은채로 특정URL에 접속해야지만 진짜 가입상태가 된다.
//        user.setCertified(certifiedKey());
        System.out.println("\n 변환이후 \n" + user);
        userService.createUser(user);
        return "redirect:/user/login";
    }
    private String certifiedKey() {
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

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String loginResult() {
        return "index";
    }

    // 마이페이지
    @GetMapping("/user/user")
    public String user() {
        return "user";
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

    // @AuthenticationPrincipal은 평소에는 null만 나오지만
    // 로그인을 하면 userDto의 정보들이 나옵니다.
    @GetMapping("/user/authPrincipal")
    public String authPrincipal(@AuthenticationPrincipal UserDto user) {

        System.out.println("@Authen 사용 user 정보"+ user);
        System.out.println("@Authen 사용 user email"+ user.getEmail() + user.getId());
        return "index";
    }
    ///////////////////////// 퍼블리싱 연결용 컨트롤러

    @GetMapping("community")
    public String community() {
        return "community";
    }
    @GetMapping("index")
    public String index() {
        return "index";
    }
    // Security 설정으로 user 필수
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }
    @GetMapping("loginAlert")
    public String loginAlert() {
        return "loginAlert";
    }
    @GetMapping("notice")
    public String notice() {
        return "notice";
    }
    @GetMapping("noticeList")
    public String noticeList() {
        return "noticeList";
    }
    @GetMapping("pwFind")
    public String pwFind() {
        return "pwFind";
    }
    @GetMapping("pwFindReset")
    public String pwFindReset() {
        return "pwFindReset";
    }
    @GetMapping("/user/signUp")
    public String signUp()
    {
        return "signUp";
    }

    @GetMapping("text")
    public String text() {
        return "text";
    }

}
