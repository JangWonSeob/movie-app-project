package com.website.movie.biz.service;

import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.dto.MovieDto;
import com.website.movie.biz.dto.TvDto;

import java.util.List;

public interface TvService {

    void getTmdbTvData();

    List<TvDto> index();
    List<CodeDto> main();

    List<TvDto> gets(TvDto parameter);
    int totalCount(TvDto parameter);
    TvDto get(TvDto parameter);
}
