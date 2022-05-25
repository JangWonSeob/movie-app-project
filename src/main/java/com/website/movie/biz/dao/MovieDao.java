package com.website.movie.biz.dao;

import com.website.movie.biz.dto.MovieDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieDao {

    int insert(MovieDto parameter);
    int update(MovieDto parameter);
    int delete(MovieDto parameter);

    MovieDto selectOne(MovieDto model);

    List<MovieDto> selectList(MovieDto model);
    int selectListCount(MovieDto model);

}
