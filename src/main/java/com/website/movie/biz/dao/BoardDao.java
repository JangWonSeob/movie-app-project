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

    BoardDto selectOne(BoardSearchModel model);

    List<BoardDto> selectList(BoardSearchModel model);
    int selectListCount(BoardSearchModel model);

}
