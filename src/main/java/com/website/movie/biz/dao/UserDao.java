package com.website.movie.biz.dao;

import com.website.movie.biz.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    UserDto selectOne(UserDto user);

    UserDto selectByEmail(String email);
    int insert(UserDto user);
    int update(UserDto user);
    int delete(UserDto user);

}
