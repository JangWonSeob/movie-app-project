package com.website.movie.web.controller.api;

import com.website.movie.biz.service.CodeService;
import com.website.movie.biz.service.TvService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiTvController {

    private final TvService tvService;
    private final CodeService codeService;

//    @PostMapping("/api/movie/get.api")
//    @ApiOperation(value = "영화 한 건 조회 API", notes = "영화 한 건에 대해서 조회 가능합니다.")
//    public JsonResult get(@RequestBody MovieDto parameter) {
//
//        MovieDto result = movieService.get(parameter);
//
//        if(result == null) {
//            return JsonResult.fail(" 입력 값이 옳지 않습니다. ");
//        }
//
//        return JsonResult.success(result);
//    }
//
//    @PostMapping("/api/movie/gets.api")
//    @ApiOperation(value = "영화 리스트 조회 API", notes = "영화 리스트 조회가 가능합니다.")
//    public JsonResult gets(@RequestBody MovieDto parameter) {
//
//        if(parameter.getSearchGenre() != null && "".equals(parameter.getSearchGenre())) {
//            parameter.setSearchGenre("12");
//        }
//
//        CodeDto code = codeService.getBySubId(CodeDto.builder().subId(parameter.getSearchGenre()).build());
//
//        if (code == null) {
//            return JsonResult.fail("올바르지 않은 값입니다.");
//        }
//
//        parameter.initPage();
//
//        List<MovieDto> list = movieService.gets(parameter);
//        int totalCount = movieService.totalCount(parameter);
//
//        Map<String, Object> result = new HashMap<>();
//
//        result.put("list", list);
//        result.put("totalCount", totalCount);
//
//        return JsonResult.success(result);
//    }
//
    @PostMapping("/api/tv/tmdb/add.api")
    public void tmdb() {

        tvService.getTmdbTvData();
    }



}
