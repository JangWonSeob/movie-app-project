package com.website.movie.biz.dao;

import com.website.movie.biz.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    UserDto selectOne(UserDto user);
    UserDto selectUser(int id);
    UserDto selectByEmail(String email);
    int insertUser(UserDto user);
    int update(UserDto user);
    int delete(UserDto user);
    UserDto emailCertifiedCheck(UserDto user);
    void emailCertifiedUpdate(UserDto user);

}
