package com.website.movie.biz.dao;

import com.website.movie.biz.dto.BookMarkDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMarkDao {

    int insert(BookMarkDto parameter);
    int delete(BookMarkDto parameter);

    BookMarkDto selectOne(BookMarkDto parameter);

    boolean selectMyBookMark(BookMarkDto parameter);

}
