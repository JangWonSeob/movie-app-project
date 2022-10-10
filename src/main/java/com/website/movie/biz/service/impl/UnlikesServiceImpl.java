package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.LikesDao;
import com.website.movie.biz.dao.UnlikesDao;
import com.website.movie.biz.dto.LikesDto;
import com.website.movie.biz.dto.UnlikesDto;
import com.website.movie.biz.service.UnlikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnlikesServiceImpl implements UnlikesService {

    private final LikesDao likesDao;
    private final UnlikesDao unlikesDao;


    @Override
    public boolean set(UnlikesDto parameter) {

        boolean result = false;

        LikesDto likes = likesDao.selectOneByBoardIdAndUserID(LikesDto.builder().boardId(parameter.getBoardId()).loginUserId(parameter.getLoginUserId()).build());
        UnlikesDto unlikes = unlikesDao.selectOneByBoardIdAndUserID(parameter);


        if(unlikes == null) {
            // 싫어요가 없으면
            if(likes != null) {
                // 좋아요가 있으면
                likes.setLoginUserId(parameter.getLoginUserId());
                likesDao.delete(likes);
            }
            unlikesDao.insert(parameter);
            result = true;
        } else {
            // 싫어요가 있으면
            unlikes.setLoginUserId(parameter.getLoginUserId());
            unlikesDao.delete(unlikes);
        }

        return result;
    }


}
