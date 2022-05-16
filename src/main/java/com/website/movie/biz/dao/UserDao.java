package com.website.movie.biz.dao;

import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.search.UserSearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    int insert(UserDto parameter);
    int update(UserDto parameter);
    int delete(UserDto parameter);

    UserDto selectOne(UserSearchModel model);
    List<UserDto> selectList(UserSearchModel model);
    int selectListCount(UserSearchModel model);
    UserDto email_certified_check(UserDto parameter);
    void email_certified_update(UserDto parameter);

}
