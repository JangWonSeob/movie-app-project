package com.website.movie.biz.service;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;

import java.util.List;

public interface BoardService {

    boolean set(BoardDto parameter);

    BoardDto get(BoardDto parameter);
    List<BoardDto> gets(BoardDto parameter);
    int totalCount(BoardDto parameter);

    void viewCountUp(BoardDto parameter);

    boolean delete(BoardDto parameter);
}
