package com.website.movie.biz.service;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.input.UserInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;
import com.website.movie.biz.model.search.UserSearchModel;

import java.util.List;

public interface UserService {

//    UserDto gets(UserInputModel model);

    void insertUser(UserInputModel model);
    // 유저한명에 대한 상세 정보 반환
    UserDto getUser(UserSearchModel model);
    List<UserDto> getUsers(UserSearchModel model);






}
