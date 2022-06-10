package com.website.movie.biz.dto;

import com.website.movie.biz.model.input.UserInputModel;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto implements UserDetails {

    private int id;


    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    private String name;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="^[a-zA-Z0-9]{8,20}$",
            message = "비밀번호는 영문 대,소문자와 숫자 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
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
