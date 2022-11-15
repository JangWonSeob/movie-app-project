package com.website.movie.biz.service;

import com.website.movie.biz.dto.CommentDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.CommentInputModel;
import com.website.movie.biz.model.search.CommentSearchModel;

import java.util.List;

public interface CommentService {

    boolean set(CommentDto parameter);

    CommentDto get(CommentDto parameter);
    List<CommentDto> getAll(CommentDto parameter);
    List<CommentDto> gets(CommentDto parameter);
    int totalCount(CommentDto parameter);

    boolean delete(CommentDto parameter);

}
