package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.UserDao;
import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.input.UserInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;
import com.website.movie.biz.model.search.UserSearchModel;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public void insertUser(UserInputModel model) {
        userDao.insertUser(UserDto.toDto(model));
    }

    @Override
    public UserDto getUser(UserSearchModel model) {
        return userDao.selectOne(model);
    }

    @Override
    public List<UserDto> getUsers(UserSearchModel model) {
        return null;
    }

//    @Override
//    public List<UserDto> gets() {
//        return userDao.select();
//    }
//

//    @Override
//    public UserDto gets(UserInputModel model) {
//        return null;
//    }



}
