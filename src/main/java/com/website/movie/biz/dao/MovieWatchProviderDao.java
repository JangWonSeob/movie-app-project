package com.website.movie.biz.dao;

import com.website.movie.biz.dto.MovieWatchProvidersDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieWatchProviderDao {

    int insert(MovieWatchProvidersDto parameter);
    int update(MovieWatchProvidersDto parameter);
    int delete(MovieWatchProvidersDto parameter);

    MovieWatchProvidersDto selectOne(MovieWatchProvidersDto parameter);

    List<MovieWatchProvidersDto> selectList(MovieWatchProvidersDto parameter);
    int selectListCount(MovieWatchProvidersDto parameter);

    List<MovieWatchProvidersDto> main(MovieWatchProvidersDto parameter);

    int deleteByMovieId(MovieWatchProvidersDto parameter);

}
