package com.website.movie.biz.dao;

import com.website.movie.biz.dto.UnlikesDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UnlikesDao {

    int insert(UnlikesDto parameter);
    int delete(UnlikesDto parameter);

    UnlikesDto selectOne(UnlikesDto parameter);
    UnlikesDto selectOneByBoardIdAndUserID(UnlikesDto parameter);

    int selectListCount(UnlikesDto parameter);

    boolean selectMyUnlike(UnlikesDto parameter);

}
