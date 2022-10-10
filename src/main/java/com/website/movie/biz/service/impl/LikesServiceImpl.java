package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.LikesDao;
import com.website.movie.biz.dao.UnlikesDao;
import com.website.movie.biz.dto.LikesDto;
import com.website.movie.biz.dto.UnlikesDto;
import com.website.movie.biz.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesDao likesDao;
    private final UnlikesDao unlikesDao;


    @Override
    public boolean set(LikesDto parameter) {

        boolean result = false;

        LikesDto likes = likesDao.selectOneByBoardIdAndUserID(parameter);
        UnlikesDto unlikes = unlikesDao.selectOneByBoardIdAndUserID(UnlikesDto.builder().boardId(parameter.getBoardId()).loginUserId(parameter.getLoginUserId()).build());

        if(likes == null) {
            // 좋아요가 없으면
            if(unlikes != null) {
                // 싫어요가 있으면
                unlikes.setLoginUserId(parameter.getLoginUserId());
                unlikesDao.delete(unlikes);
            }
            likesDao.insert(parameter);
            result = true;
        } else {
            // 좋아요가 있으면
            likes.setLoginUserId(parameter.getLoginUserId());
            likesDao.delete(likes);
        }

        return result;
    }

}
