package com.website.movie.biz.dao;

import com.website.movie.biz.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    UserDto selectOne(UserDto user);
    UserDto selectUser(int id);
    UserDto findByEmail(String email);
    List<String> selectAuthorityByEmail(String email);
    int createUser(UserDto user);
    //유저의 타입을 만들어주는 함수 //int id는 가입하는 회원의 고유키(ID)에 매칭시키기 위해 필요하다
    void createAuthority(UserDto user);
    int update(UserDto user);
    int delete(UserDto user);
    void deleteAuthority(int id);
    UserDto getById(int id);
    UserDto email_certified_check(UserDto user);
    void email_certified_update(UserDto user);

}
