package com.website.movie.biz.service;

import com.website.movie.biz.dto.CodeDto;

import java.util.List;

public interface CodeService {

    CodeDto get(CodeDto parameter);
    CodeDto getBySubId(CodeDto parameter);
    List<CodeDto> gets(CodeDto parameter);

}
