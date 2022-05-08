package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.BoardDao;
import com.website.movie.biz.dao.CommentDao;
import com.website.movie.biz.dao.UserDao;
import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;
import com.website.movie.biz.model.search.CommentSearchModel;
import com.website.movie.biz.service.BoardService;
import com.website.movie.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private final CommentDao commentDao;

    @Override
    public boolean set(BoardInputModel model) {

        int affected;

        if(model.getId() > 0) {
            // 수정
            affected = boardDao.update(BoardDto.toDto(model));
        } else {
            // 생성
            affected = boardDao.insert(BoardDto.toDto(model));
        }

        if (affected < 1) {
            return false;
        }

        return true;
    }

    @Override
    public BoardDto get(BoardSearchModel model) {

        BoardDto result = boardDao.selectOne(model);
        result.setComments(commentDao.selectList(CommentSearchModel.builder().boardId(result.getId()).build()));
        result.setCommentTotalCount(commentDao.selectListCount(CommentSearchModel.builder().boardId(result.getId()).build()));

        return result;
    }

    @Override
    public List<BoardDto> gets(BoardSearchModel model) {
        return boardDao.selectList(model);
    }

    @Override
    public int totalCount(BoardSearchModel model) {
        return boardDao.selectListCount(model);
    }

    @Override
    public void viewCountUp(BoardSearchModel model) {

        BoardDto parameter = new BoardDto();
        parameter.setId(model.getId());
        parameter.setSqlUpdateType("VIEW_COUNT_UP");
        boardDao.update(parameter);
    }

    @Override
    public boolean delete(BoardInputModel model) {

        if(model.getId() < 1) {
            return false;
        }

        int affected = boardDao.delete(BoardDto.toDto(model));

        if (affected < 1) {
            return false;
        }

        return true;
    }


}
