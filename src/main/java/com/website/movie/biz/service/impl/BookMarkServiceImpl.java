package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.BookMarkDao;
import com.website.movie.biz.dto.BookMarkDto;
import com.website.movie.biz.service.BookMarkService;
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
            return true;
        } else {
            affected = bookMarkDao.delete(parameter);
            return false;
        }
//        if (affected < 1) {
//        }

    }



}
