package com.website.movie.web.controller.api;

import com.website.movie.biz.component.MovieComponent;
import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.dto.MovieDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.service.CodeService;
import com.website.movie.biz.service.MovieService;
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
public class ApiMovieController {

    private final MovieService movieService;
    private final CodeService codeService;
    private final MovieComponent movieComponent;


    @PostMapping("/api/movie/get.api")
    @ApiOperation(value = "영화 한 건 조회 API", notes = "영화 한 건에 대해서 조회 가능합니다.")
    public JsonResult get(@RequestBody MovieDto parameter) {

        MovieDto result = movieService.get(parameter);

        if(result == null) {
            return JsonResult.fail(" 입력 값이 옳지 않습니다. ");
        }

        return JsonResult.success(result);
    }

    @PostMapping("/api/movie/gets.api")
    @ApiOperation(value = "영화 리스트 조회 API", notes = "영화 리스트 조회가 가능합니다.")
    public JsonResult gets(@RequestBody MovieDto parameter) {

        if(parameter.getSearchGenre() != null && "".equals(parameter.getSearchGenre())) {
            parameter.setSearchGenre("12");
        }

        CodeDto code = codeService.getBySubId(CodeDto.builder().subId(parameter.getSearchGenre()).build());

        if (code == null) {
            return JsonResult.fail("올바르지 않은 값입니다.");
        }

        parameter.initPage();

        List<MovieDto> list = movieService.gets(parameter);
        int totalCount = movieService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/movie/tmdb/add.api")
    public void tmdb() {

        movieService.getTmdbMovieData();
    }

    @PostMapping("/api/movie/provider.api")
    public void getProviders() {
        movieComponent.getProviders();
    }

    @PostMapping("/api/movie/detail.api")
    public void getDetail() {
        movieComponent.getMovieDetail("675353");
    }


}
