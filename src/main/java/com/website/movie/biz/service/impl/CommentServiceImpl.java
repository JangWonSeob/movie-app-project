package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.CommentDao;
import com.website.movie.biz.dto.CommentDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.CommentInputModel;
import com.website.movie.biz.model.search.CommentSearchModel;
import com.website.movie.biz.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    public boolean set(CommentInputModel model) {

        int affected;

        if (model.getId() > 0) {
            // 수정
            affected = commentDao.update(CommentDto.toDto(model));
        } else {
            // 생성
            affected = commentDao.insert(CommentDto.toDto(model));
        }

        if (affected < 1) {
            return false;
        }

        return true;
    }


    @Override
    public List<CommentDto> gets(CommentSearchModel model) {
        return commentDao.selectList(model);
    }

    @Override
    public int totalCount(CommentSearchModel model) {
        return commentDao.selectListCount(model);
    }

    @Override
    public boolean delete(CommentInputModel model) {

        if (model.getId() < 1) {
            return false;
        }

        int affected = commentDao.delete(CommentDto.toDto(model));

        if (affected < 1) {
            return false;
        }

        return true;
    }


}
