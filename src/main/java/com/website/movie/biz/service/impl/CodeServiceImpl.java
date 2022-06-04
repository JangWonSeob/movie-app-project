package com.website.movie.biz.service.impl;

import com.website.movie.biz.dao.CodeDao;
import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final CodeDao codeDao;

    @Override
    public CodeDto get(CodeDto parameter) {
        return codeDao.selectOne(parameter);
    }

    @Override
    public CodeDto getBySubId(CodeDto parameter) {
        return codeDao.selectOneBySubId(parameter);
    }


}
