package com.website.movie.biz.dao;

import com.website.movie.biz.dto.TvDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TvDao {

    int insert(TvDto parameter);
    int update(TvDto parameter);
    int delete(TvDto parameter);

    List<TvDto> index();

    TvDto selectOne(TvDto parameter);

    List<TvDto> selectList(TvDto parameter);
    int selectListCount(TvDto parameter);

    List<TvDto> main(TvDto parameter);

}
