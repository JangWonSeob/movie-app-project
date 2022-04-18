package com.website.movie.biz.service;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;

import java.util.List;

public interface BoardService {

    boolean set(BoardInputModel model);

    BoardDto get(BoardSearchModel model);
    List<BoardDto> gets(BoardSearchModel model);
    int totalCount(BoardSearchModel model);
}
