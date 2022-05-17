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

        LikesDto likes = likesDao.selectOne(LikesDto.builder().boardId(parameter.getBoardId()).loginUserId(parameter.getLoginUserId()).build());
        UnlikesDto unlikes = unlikesDao.selectOne(parameter);

        int affected;

        if(unlikes == null) {
            // 싫어요가 없으면
            if(likes != null) {
                // 좋아요가 있으면
                likesDao.delete(likes);
            }
            affected =unlikesDao.insert(parameter);
        } else {
            // 싫어요가 있으면
            affected =unlikesDao.delete(unlikes);
        }

        if (affected < 1) {
            return false;
        }
        return true;
    }


}
