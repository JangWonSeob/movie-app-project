package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.LikesDto;
import com.website.movie.biz.dto.UnlikesDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.service.LikesService;
import com.website.movie.biz.service.UnlikesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiUnlikesController {

    private final UnlikesService unlikesService;

    @PostMapping("/api/unlikes/set.api")
    @ApiOperation(value = "싫어요 등록 API", notes = "싫어요 등록 가능합니다.")
    public JsonResult set(@RequestBody UnlikesDto parameter) {

//        로그인 유저 추가 로직
        parameter.setLoginUserId(1);

        boolean result = unlikesService.set(parameter);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }
}
