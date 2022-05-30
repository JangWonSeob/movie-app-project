package com.website.movie.web.controller;

import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final UserService userService;
    private final JavaMailSender mailSender;

    @GetMapping("/mailCheck")
    @ResponseBody
    public String mailCheck(@RequestParam("email") String email) throws Exception{
        System.out.println("\n mailCheck \n"+ email);
        int serti = (int)((Math.random()*(99999 - 10000 + 1)) + 10000);
        String from = "project.movieweb@gmail.com";
        //보내는 이 메일주소
        String to = email;
        String title = "회원가입시 필요한 인증번호 입니다.";
        String content = "[인증번호] "+ serti +" 입니다. <br/> 인증번호 확인란에 기입해주십시오.";
        String num = "";
//        if (중복된 이메일 존재하면) {num = "overlapError"}  else {정상수행}
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(title);
            mailHelper.setText(content, true);
            mailSender.send(mail);
            num = Integer.toString(serti);
        } catch(Exception e) {
            num = "error";
        }
        System.out.println("\n num 값 \n" + num);
        return num;
    }

}
