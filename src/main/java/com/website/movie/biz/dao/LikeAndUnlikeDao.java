package com.website.movie.biz.dao;

import com.website.movie.biz.dto.LikeAndUnlikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikeAndUnlikeDao {

    int insert(LikeAndUnlikeDto parameter);
    int update(LikeAndUnlikeDto parameter);

    List<LikeAndUnlikeDto> selectList(LikeAndUnlikeDto parameter);
    int selectListCount(LikeAndUnlikeDto parameter);


}
