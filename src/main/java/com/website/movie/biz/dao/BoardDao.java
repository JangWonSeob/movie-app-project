package com.website.movie.biz.dao;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.model.search.BoardSearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {

    int insert(BoardDto parameter);
    int update(BoardDto parameter);
    int delete(BoardDto parameter);

    List<BoardDto> index(BoardDto parameter);
    BoardDto selectOne(BoardDto parameter);

    List<BoardDto> selectList(BoardDto parameter);
    int selectListCount(BoardDto parameter);

}
