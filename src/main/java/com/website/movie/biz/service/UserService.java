package com.website.movie.biz.service;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.UserInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;
import com.website.movie.biz.model.search.UserSearchModel;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {

    boolean set(UserInputModel model);
    // 유저한명에 대한 상세 정보 반환
    UserDto get(UserSearchModel model);
    List<UserDto> gets(UserSearchModel model);
    boolean delete(UserInputModel model);
    void insert(UserDto user);
    PasswordEncoder passwordEncoder();

//    // 중복 ID 여부를 검증하는 메서드
//    boolean CheckDuplicate(String email);
//    // 로그인 유효성을 검증하는 메서드
//    int userCheck(String email, String password);
//    // 존재하는 이메일인지 확인하는 메서드(유효: 1, 중복: -1)
//    int userCheckEmail(int id);

    public UserDto email_certified_check(UserDto user);
    public void email_certified_update(UserDto user);
}
