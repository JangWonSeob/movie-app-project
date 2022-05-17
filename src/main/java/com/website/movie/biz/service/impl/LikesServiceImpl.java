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

        LikesDto likes = likesDao.selectOne(parameter);
        UnlikesDto unlikes = unlikesDao.selectOne(UnlikesDto.builder().boardId(parameter.getBoardId()).loginUserId(parameter.getLoginUserId()).build());

        int affected;

        if(likes == null) {
            // 좋아요가 없으면
            if(unlikes != null) {
                // 싫어요가 있으면
                unlikesDao.delete(unlikes);
            }
            affected = likesDao.insert(parameter);
        } else {
            // 좋아요가 있으면
            affected = likesDao.delete(likes);
        }

        if (affected < 1) {
            return false;
        }
        return true;
    }

}
