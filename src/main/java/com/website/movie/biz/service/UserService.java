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

    boolean set(UserDto parameter);
    // 유저한명에 대한 상세 정보 반환
    UserDto get(UserDto model);
    List<UserDto> gets(UserDto model);
    boolean delete(UserDto model);
    void insert(UserDto user);
    Collection<GrantedAuthority> getUsertype(int id);
    public UserDto email_certified_check(UserDto user);
    public void email_certified_update(UserDto user);
    PasswordEncoder passwordEncoder();

}
