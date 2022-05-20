package com.website.movie.web.controller;

import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.service.EmailService;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;

    private final JavaMailSender mailSender;

    // 아직 유저가 없으니 UserDto user 를 받지 못한다.
    // 그렇기 때문에 화면에 있는
    //ajax로 이용
    @GetMapping(value = "/user/email/send")
    public void sendmail(UserDto user) throws MessagingException {
        System.out.println("\n 인증키 설정 전 user \n" + user);
        user.setCertified(certified_key());
        System.out.println("\n 인증키 설정 후 user \n" + user);
        
        StringBuffer emailcontent = new StringBuffer();
        emailcontent.append("<!DOCTYPE html>");
        emailcontent.append("<html>");
        emailcontent.append("<head>");
        emailcontent.append("</head>");
        emailcontent.append("<body>");
        emailcontent.append(
                " <div" 																																																	+
                        "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #02b875; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+
                        "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"																															+
                        "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">12수 2022</span><br />"																													+
                        "		<span style=\"color: #02b875\">메일인증</span> 안내입니다."																																				+
                        "	</h1>\n"																																																+
                        "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+
                            user.getEmail()																																													+
                        "		님 안녕하세요.<br />"																																													+
                        "		영화추천 서비스에 가입해 주셔서 진심으로 감사드립니다.<br />"																																						+
                        "		아래 <b style=\"color: #02b875\">'메일 인증'</b> 버튼을 클릭하여 회원가입을 완료해 주세요.<br />"																													+
                        "		감사합니다."																																															+
                        "	</p>"																																																	+
                        "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\""																																	+
                        "	href=\"http://localhost:8080/user/email/certified?email=" + user.getEmail() + "&certified=" + user.getCertified() + "\" target=\"_blank\">"														+
                        "		<p"																																																	+
                        "			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #02b875; line-height: 45px; vertical-align: middle; font-size: 16px;\">"							+
                        "			메일 인증</p>"																																														+
                        "	</a>"																																																	+
                        "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"																																		+
                        " </div>"
        );
        emailcontent.append("</body>");
        emailcontent.append("</html>");
        System.out.println(user);
        emailService.sendMail(user.getEmail(), "[12수2022 이메일 인증]", emailcontent.toString());
    }
    // 랜덤 키값 만들기
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
    //sendmail로 받은 메일에서 인증하기를 눌렀을때
    @GetMapping(value = "/user/email/certified")
    @Transactional
    public ModelAndView checkmail(HttpServletRequest request, UserDto user) throws MessagingException {
        HttpSession session = request.getSession();
        UserDto u = userService.email_certified_check(user);

        if(u != null) {
            userService.email_certified_update(user);
            SecurityContextHolder.getContext().setAuthentication(null);
            session.removeAttribute("Authorization");
        }

        return new ModelAndView("emailChecksuccess");
    }
    @GetMapping("/user/mailCheck")
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
