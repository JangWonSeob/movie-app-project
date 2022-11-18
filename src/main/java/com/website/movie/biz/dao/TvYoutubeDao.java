package com.website.movie.biz.dao;

import com.website.movie.biz.dto.TvYoutubeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TvYoutubeDao {

    int insert(TvYoutubeDto parameter);
    int update(TvYoutubeDto parameter);
    int delete(TvYoutubeDto parameter);

    TvYoutubeDto selectOne(TvYoutubeDto parameter);

    List<TvYoutubeDto> selectList(TvYoutubeDto parameter);

    int deleteByTvId(TvYoutubeDto parameter);

}
