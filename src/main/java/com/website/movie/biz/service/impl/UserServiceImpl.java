package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.UserDao;
import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.UserInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;
import com.website.movie.biz.model.search.CommentSearchModel;
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
    public boolean set(UserInputModel model) {
        int affected;

        if(model.getId() > 0){
            affected = userDao.update(UserDto.toDto(model));
        }  else {
            affected = userDao.insert(UserDto.toDto(model));
        }

        if (affected < 1) {
            return false;
        }
        return true;
    }

    @Override
    public UserDto get(UserSearchModel model) {
        UserDto result = userDao.selectOne(model);

        return result;
    }

    @Override
    public List<UserDto> gets(UserSearchModel model) {
        return userDao.selectList(model);
    }

    @Override
    public boolean delete(UserInputModel model) {

        if(model.getId() < 1) {
            return false;
        }

        int affected = userDao.delete(UserDto.toDto(model));

        if (affected < 1) {
            return false;
        }

        return true;
    }

}
