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

        boolean result = false;

        BookMarkDto bookMarkDto = bookMarkDao.selectOne(parameter);

        if(bookMarkDto == null) {
            bookMarkDao.insert(parameter);
            result = true;
        } else {
            bookMarkDao.delete(parameter);
        }

        return result;
    }



}
