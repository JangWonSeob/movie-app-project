package com.website.movie.biz.service;

import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.dto.MovieDto;

import java.util.List;

public interface MovieService {

    void getTmdbMovieData();

    List<MovieDto> index();
    List<CodeDto> main();

    List<MovieDto> gets(MovieDto parameter);
    int totalCount(MovieDto parameter);
    MovieDto get(MovieDto parameter);
}
