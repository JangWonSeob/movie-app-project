package com.website.movie.biz.dao;

import com.website.movie.biz.dto.CodeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeDao {

    int insert(CodeDto parameter);

    List<CodeDto> selectList(CodeDto parameter);
    int selectListCount(CodeDto parameter);

    CodeDto selectOne(CodeDto parameter);
    CodeDto selectOneBySubId(CodeDto parameter);

}
