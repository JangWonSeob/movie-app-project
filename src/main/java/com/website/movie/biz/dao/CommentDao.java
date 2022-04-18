package com.website.movie.biz.dao;

import com.website.movie.biz.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {

    List<CommentDto> selectList();
    int selectListCount();
}
