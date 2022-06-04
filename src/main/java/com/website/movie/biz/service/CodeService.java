package com.website.movie.biz.service;

import com.website.movie.biz.dto.CodeDto;

public interface CodeService {

    CodeDto get(CodeDto parameter);
    CodeDto getBySubId(CodeDto parameter);

}
