package com.website.movie.biz.dao;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.search.UserSearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<UserDto> select();

    UserDto selectOne(UserSearchModel model);

    List<UserDto> selectList();
    int selectListCount();

    void insertUser(UserDto userDto);

}
