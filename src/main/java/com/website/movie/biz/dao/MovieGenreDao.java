package com.website.movie.biz.dao;

import com.website.movie.biz.dto.MovieGenreDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieGenreDao {

    @Insert(" INSERT INTO movie_genre " +
            " (MOVIE_ID, GENRE_ID, DEL_YN) " +
            " VALUES " +
            " (#{movieId}, #{genreId}, 0) ")
    int insert(MovieGenreDto parameter);
//    int update(MovieGenreDto parameter);
//    int delete(MovieGenreDto parameter);
//
//    MovieGenreDto selectOne(MovieGenreDto model);
//
//    List<MovieGenreDto> selectList(MovieGenreDto model);
//    int selectListCount(MovieGenreDto model);

}
