package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.BookMarkDao;
import com.website.movie.biz.dao.LikesDao;
import com.website.movie.biz.dto.BookMarkDto;
import com.website.movie.biz.dto.LikesDto;
import com.website.movie.biz.service.BookMarkService;
import com.website.movie.biz.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMarkServiceImpl implements BookMarkService {

    private final BookMarkDao bookMarkDao;


    @Override
    public boolean set(BookMarkDto parameter) {

        BookMarkDto bookMarkDto = bookMarkDao.selectOne(parameter);

        int affected;

        if(bookMarkDto == null) {
            affected = bookMarkDao.insert(parameter);
        } else {
            affected = bookMarkDao.delete(parameter);
        }
        if (affected < 1) {
            return false;
        }

        return true;
    }



}
