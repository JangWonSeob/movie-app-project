package com.website.movie.biz.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCertifiedDao {
    String select(int id);


}
