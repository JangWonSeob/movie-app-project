package com.website.movie.biz.service;

import com.website.movie.biz.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> main(MovieDto parameter);
    void getTmdbMovieData();

    List<MovieDto> gets(MovieDto parameter);
    int totalCount(MovieDto parameter);
    MovieDto get(MovieDto parameter);
}
