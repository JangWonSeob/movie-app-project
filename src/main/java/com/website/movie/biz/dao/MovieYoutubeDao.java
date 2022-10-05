package com.website.movie.biz.dao;

import com.website.movie.biz.dto.MovieYoutubeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieYoutubeDao {

    int insert(MovieYoutubeDto parameter);
    int update(MovieYoutubeDto parameter);
    int delete(MovieYoutubeDto parameter);

    MovieYoutubeDto selectOne(MovieYoutubeDto parameter);

    List<MovieYoutubeDto> selectList(MovieYoutubeDto parameter);

    int deleteByMovieId(MovieYoutubeDto parameter);

}
