package com.website.movie.biz.service;

import com.website.movie.biz.dto.CommentDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.CommentInputModel;
import com.website.movie.biz.model.search.CommentSearchModel;

import java.util.List;

public interface CommentService {

    boolean set(CommentInputModel model);

    List<CommentDto> gets(CommentSearchModel model);
    int totalCount(CommentSearchModel model);

    boolean delete(CommentInputModel model);

}
