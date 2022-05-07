package com.website.movie.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity  // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/user/**").authenticated() // /user 페이지는 로그인을 해야 사용가능
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") 
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // /admin/** 주소는  ADMIN Role이 있어야 사용가능
                .anyRequest().permitAll()  //위에 설정한 주소가 아니면 누구나 이용가능
                .and()    //접근권한이 없을때
                .formLogin()
                .loginPage("/login");
    }
}
