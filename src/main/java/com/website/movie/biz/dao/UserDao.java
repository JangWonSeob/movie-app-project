package com.website.movie.biz.dao;

import com.website.movie.biz.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    UserDto selectOne(UserDto parameter);
    UserDto selectUser(int parameter);
    List<String> selectUsertype(int id);
    int insert(UserDto parameter);
    void insertUsertype(UserDto parameter);
    int update(UserDto parameter);
    int delete(UserDto parameter);
    List<UserDto> selectList(UserDto parameter);
    int selectListCount(UserDto parameter);
    UserDto email_certified_check(UserDto parameter);
    void email_certified_update(UserDto parameter);

}
