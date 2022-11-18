package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.dto.TvDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.service.CodeService;
import com.website.movie.biz.service.TvService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiTvController {

    private final TvService tvService;
    private final CodeService codeService;

    @PostMapping("/api/tv/get.api")
    @ApiOperation(value = "TV 한 건 조회 API", notes = "TV 한 건에 대해서 조회 가능합니다.")
    public JsonResult get(@RequestBody TvDto parameter) {

        TvDto result = tvService.get(parameter);

        if(result == null) {
            return JsonResult.fail(" 입력 값이 옳지 않습니다. ");
        }

        return JsonResult.success(result);
    }

    @PostMapping("/api/tv/gets.api")
    @ApiOperation(value = "TV 리스트 조회 API", notes = "TV 리스트 조회가 가능합니다.")
    public JsonResult gets(@RequestBody TvDto parameter) {

        if(parameter.getSearchGenre() != null && "".equals(parameter.getSearchGenre())) {
            parameter.setSearchGenre("10759");
        }

        CodeDto code = codeService.getBySubId(CodeDto.builder().subId(parameter.getSearchGenre()).build());

        if (code == null) {
            return JsonResult.fail("올바르지 않은 값입니다.");
        }

        parameter.initPage();

        List<TvDto> list = tvService.gets(parameter);
        int totalCount = tvService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/tv/search.api")
    @ApiOperation(value = "TV 검색 조회 API", notes = "TV 검색 조회가 가능합니다.")
    public JsonResult search(@RequestBody TvDto parameter) {

        parameter.initPage2();

        List<TvDto> list = tvService.gets(parameter);
        int totalCount = tvService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/tv/tmdb/add.api")
    public void tmdb() {

        tvService.getTmdbTvData();
    }



}
