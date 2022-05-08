package com.website.movie.biz.dao;

import com.website.movie.biz.dto.CommentDto;
import com.website.movie.biz.model.search.CommentSearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {

    int insert(CommentDto parameter);
    int delete(CommentDto parameter);
    int update(CommentDto parameter);

    List<CommentDto> selectList(CommentSearchModel model);
    int selectListCount(CommentSearchModel model);

}
