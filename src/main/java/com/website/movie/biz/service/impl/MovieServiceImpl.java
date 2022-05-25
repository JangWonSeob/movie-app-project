package com.website.movie.biz.service.impl;

import com.website.movie.biz.component.MovieComponent;
import com.website.movie.biz.dao.MovieDao;
import com.website.movie.biz.dao.MovieGenreDao;
import com.website.movie.biz.dto.MovieDto;
import com.website.movie.biz.dto.MovieGenreDto;
import com.website.movie.biz.model.movie.MovieData;
import com.website.movie.biz.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;
    private final MovieGenreDao movieGenreDao;
    private final MovieComponent movieComponent;

    @Override
    public void getTmdbMovieData() {

        for (int i = 1; i < 11; i++) {
            List<MovieData> list = movieComponent.getMovieList(i);
            for (MovieData movieData : list) {

                MovieDto parameter = MovieDto.toDto(movieData);

                MovieDto movie = movieDao.selectOne(parameter);

                if (movie == null) {
                    movieDao.insert(parameter);

                    String[] movieGenre = movieData.getGenreIds();
                    if(movieGenre != null && movieGenre.length > 0) {
                        for (String genre : movieGenre) {
                            movieGenreDao.insert(MovieGenreDto.builder().movieId(parameter.getId()).genreId(genre).build());
                        }
                    }
                }
            }
        }
    }


}
