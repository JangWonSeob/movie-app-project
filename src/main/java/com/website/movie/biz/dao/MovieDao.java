package com.website.movie.biz.dao;

import com.website.movie.biz.dto.MovieDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieDao {

    int insert(MovieDto parameter);
    int update(MovieDto parameter);
    int delete(MovieDto parameter);

    List<MovieDto> index();
    List<MovieDto> index2();

    MovieDto selectOne(MovieDto parameter);

    List<MovieDto> selectList(MovieDto parameter);
    int selectListCount(MovieDto parameter);

    List<MovieDto> main(MovieDto parameter);

}
