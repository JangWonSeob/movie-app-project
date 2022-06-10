package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.UnlikesDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.service.UnlikesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiUnlikesController {

    private final UnlikesService unlikesService;

    @PostMapping("/api/unlikes/set.api")
    @ApiOperation(value = "싫어요 등록 API", notes = "싫어요 등록 가능합니다.")
    public JsonResult set(@AuthenticationPrincipal UserDto user, @RequestBody UnlikesDto parameter) {

        if (user == null) {
            return JsonResult.fail("접근 권한이 없습니다.");
        }

        parameter.setLoginUserId(user.getId());

        boolean result = unlikesService.set(parameter);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }
}
