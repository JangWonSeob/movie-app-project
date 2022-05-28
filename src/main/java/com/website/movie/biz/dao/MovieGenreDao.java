package com.website.movie.biz.dao;

import com.website.movie.biz.dto.MovieGenreDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieGenreDao {

    @Insert(" INSERT INTO movie_genre " +
            " (MOVIE_ID, GENRE_ID, DEL_YN) " +
            " VALUES " +
            " (#{movieId}, #{genreId}, 0) ")
    int insert(MovieGenreDto parameter);

    @Select(" SELECT T1.*, T2.NAME AS GENRE_NAME " +
            " FROM movie_genre " +
            " JOIN code T2 ON T1.GENRE_ID = T2.ID AND T2.TYPE = 'MOVIE_GENRE' " +
            " WHERE T1.MOVIE_ID = #{movieId} ")
    List<MovieGenreDto> selectByMovieId(MovieGenreDto parameter);

//    int update(MovieGenreDto parameter);
//    int delete(MovieGenreDto parameter);
//
//    MovieGenreDto selectOne(MovieGenreDto model);
//
//    List<MovieGenreDto> selectList(MovieGenreDto model);
//    int selectListCount(MovieGenreDto model);

}
