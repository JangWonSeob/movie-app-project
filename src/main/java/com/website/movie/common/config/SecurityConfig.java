package com.website.movie.common.config;


import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity  // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //username으로만 받는 로그인화면 파라미터를 email로 바꾼다.
        http.formLogin().usernameParameter("email");
        http.authorizeRequests()
//                .mvcMatchers("/","/css/**","/scripts/**","/plugin/**","/fonts/**")   // 로그인후 css 미적용시
//                .permitAll()
                .antMatchers("/user/admin/**").access("hasAuthority('ADMIN')")
                .antMatchers("/user/authorithy/**").access("hasAuthority('USER')") // 페이지 권한 설정
                .anyRequest().permitAll()  //위에 설정한 주소가 아니면 누구나 이용가능
                .and()    //접근권한이 없을때
                .formLogin() //아래내용은 로그인하는 경우에 대한 설정이다.
                .loginPage("/user/login")  //로그인 페이지는 /login 이다.
                .usernameParameter("email")
                .loginProcessingUrl("/user/login")  //로그인 버튼을 클릭했을시 action의 경로(기본적으로 post)
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //로그아웃 설정
                .logoutSuccessUrl("/user/logout/result").invalidateHttpSession(true)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID") //로그아웃 세션처리
                .and()
                .exceptionHandling().accessDeniedPage("/denied") // 403 예외처리 핸들링
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(userService.passwordEncoder());
    }
//    @Bean
//    public BCryptPasswordEncoder encodePwd(){
//        return new BCryptPasswordEncoder();
//    }
////    오류 해결못함
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(userService.passwordEncoder());
//    }

}
