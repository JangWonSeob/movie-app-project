package com.website.movie.biz.dao;


import com.website.movie.biz.dto.AuthorityDto;
import com.website.movie.biz.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AuthorityDao {

    AuthorityDto select(AuthorityDto authority);
    List<String> selectByEmail(String email);
    void insertAuthority(UserDto user);


}
