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
    public boolean set(CommentDto parameter) {

        int affected;

        if (parameter.getId() > 0) {
            // 수정
            affected = commentDao.update(parameter);
        } else {
            // 생성
            affected = commentDao.insert(parameter);
        }

        if (affected < 1) {
            return false;
        }
        return true;
    }


    @Override
    public List<CommentDto> gets(CommentDto parameter) {
        parameter.setSearchType("PARENTS");
        return commentDao.selectList(parameter);
    }

    @Override
    public int totalCount(CommentDto parameter) {
        return commentDao.selectListCount(parameter);
    }

    @Override
    public boolean delete(CommentDto parameter) {

        if (parameter.getId() < 1) {
            return false;
        }

        int affected = commentDao.delete(parameter);

        if (affected < 1) {
            return false;
        }

        return true;
    }


}
