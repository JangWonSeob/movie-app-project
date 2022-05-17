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

    public static UserDto toDto(UserInputModel model) {

        return UserDto.builder()
                .id(model.getId())
                .email(model.getEmail())
                .name(model.getName())
                .nickname(model.getNickname())
                .password(model.getPassword())
                .password(model.getCertified())
                .build();
    }

    //아래는 Spring Security 기본제공 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    
    @Override
    public boolean isEnabled() {
        return false;
    }
}
