package com.website.movie.biz.service;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.UserInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;
import com.website.movie.biz.model.search.UserSearchModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {

    boolean set(UserDto user);
    // 유저한명에 대한 상세 정보 반환
    UserDto get(UserDto user);
    boolean delete(UserDto user);
    //유저를 회원가입시키고 기본적인 권한을 줍니다.
    void createUser(UserDto user);
    Collection<GrantedAuthority> getAuthorities(String email);
    PasswordEncoder passwordEncoder();
    int emailCheck(UserDto user);

    int nicknameCheck(UserDto user);
    int updatePassword(UserDto user);
}
