package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.*;
import com.website.movie.biz.dto.*;
import com.website.movie.biz.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private final LikesDao likesDao;
    private final UnlikesDao unlikesDao;
    private final BookMarkDao bookMarkDao;

    @Override
    public List<BoardDto> index(BoardDto parameter) {
        return boardDao.index(parameter);
    }

    @Override
    public boolean set(BoardDto parameter) {

        int affected;

        System.out.println("set 에 parmeter 전체실행"+parameter);
        System.out.println("set 에 parmeter.getId() 실행"+parameter.getId());

        if (parameter.getId() > 0) {
            // 수정
            System.out.println("set 에 update 실행");
            affected = boardDao.update(parameter);
        } else {
            // 생성
            System.out.println("set 에 insert");
            affected = boardDao.insert(parameter);
        }

        if (affected < 1) {
            return false;
        }

        return true;
    }

    @Override
    public BoardDto get(BoardDto parameter) {

        BoardDto result = boardDao.selectOne(parameter);
        if(parameter.getLoginUserId() > 0) {
            // 나의 좋아요 여부
            result.setMyLike(likesDao.selectMyLike(LikesDto.builder().boardId(result.getId()).loginUserId(parameter.getLoginUserId()).build()));
            // 나의 싫어요 여부
            result.setMyUnlike(unlikesDao.selectMyUnlike(UnlikesDto.builder().boardId(result.getId()).loginUserId(parameter.getLoginUserId()).build()));
            // 즐겨찾기 여부
            result.setBookMarkYn(bookMarkDao.selectMyBookMark(BookMarkDto.builder().tableId(String.valueOf(result.getId())).tableName(BookMarkDto.TABLE_NAME_BOARD).loginUserId(parameter.getLoginUserId()).build()));
        }
        // 좋아요 갯수
        //
        result.setLikeTotalCount(likesDao.selectListCount(LikesDto.builder().boardId(result.getId()).build()));
        // 싫어요 갯수
//        result.setUnlikeTotalCount(unlikesDao.selectListCount(UnlikesDto.builder().boardId(result.getId()).build()));

        return result;
    }

    @Override
    public List<BoardDto> gets(BoardDto parameter, int totalCount) {

        List<BoardDto> list = boardDao.selectList(parameter);

        for(int i = 0; i < list.size(); i++) {
            BoardDto e = list.get(i);
            e.setBoardNo(totalCount - (parameter.getPageIndex() - 1) * parameter.getPageSize() - i);
        }

        return list;
    }

    @Override
    public int totalCount(BoardDto parameter) {
        return boardDao.selectListCount(parameter);
    }

    @Override
    public void viewCountUp(BoardDto parameter) {

        parameter.setId(parameter.getId());
        parameter.setSqlUpdateType("VIEW_COUNT_UP");
        boardDao.update(parameter);
    }

    @Override
    public boolean delete(BoardDto parameter) {

        if (parameter.getId() < 1) {
            return false;
        }

        int affected = boardDao.delete(parameter);

        if (affected < 1) {
            return false;
        }

        return true;
    }


}
