package com.website.movie.biz.dao;

import com.website.movie.biz.dto.LikesDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikesDao {

    int insert(LikesDto parameter);
    int delete(LikesDto parameter);

    LikesDto selectOne(LikesDto parameter);
    LikesDto selectOneByBoardIdAndUserID(LikesDto parameter);

    int selectListCount(LikesDto parameter);

    boolean selectMyLike(LikesDto parameter);

}
