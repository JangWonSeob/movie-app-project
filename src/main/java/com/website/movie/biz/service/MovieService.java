package com.website.movie.biz.service;

import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<CodeDto> main();
    void getTmdbMovieData();

    List<MovieDto> gets(MovieDto parameter);
    int totalCount(MovieDto parameter);
    MovieDto get(MovieDto parameter);
}
