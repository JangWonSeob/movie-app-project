package com.website.movie.biz.dto;

import com.website.movie.biz.model.input.UserInputModel;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto implements UserDetails {

    private int id;
    private String email;
    private String name;
    private String nickname;
    private String password;
    private String certified;
    private Collection<? extends GrantedAuthority> authorities;  //두개이상의 권한을 리스트에 담아 사용

    //아래는 Spring Security 기본제공 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // 로그인에 사용하는 아이디 get
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
