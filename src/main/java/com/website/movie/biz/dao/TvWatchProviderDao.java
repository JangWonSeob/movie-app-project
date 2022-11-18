package com.website.movie.biz.dao;

import com.website.movie.biz.dto.TvWatchProvidersDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TvWatchProviderDao {

    int insert(TvWatchProvidersDto parameter);
    int update(TvWatchProvidersDto parameter);
    int delete(TvWatchProvidersDto parameter);

    TvWatchProvidersDto selectOne(TvWatchProvidersDto parameter);

    List<TvWatchProvidersDto> selectList(TvWatchProvidersDto parameter);
    int selectListCount(TvWatchProvidersDto parameter);

    List<TvWatchProvidersDto> main(TvWatchProvidersDto parameter);

    int deleteByMovieId(TvWatchProvidersDto parameter);

}
