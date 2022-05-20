package com.website.movie.biz.dao;


import com.website.movie.biz.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AuthorityDao {

    String select(int id);
    List<String> selectByEmail(String email);
    void insertAuthority(UserDto user);
    void deleteAuthority(int id);




}
