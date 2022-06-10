package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.BookMarkDto;
import com.website.movie.biz.dto.LikesDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.service.BookMarkService;
import com.website.movie.biz.service.LikesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiBookMarkController {

    private final BookMarkService bookMarkService;

    @PostMapping("/api/book-mark/set.api")
    @ApiOperation(value = "즐겨찾기 등록 및 삭제 API", notes = "즐겨찾기 등록 및 삭제가 가능합니다.")
    public JsonResult add(@AuthenticationPrincipal UserDto user, @RequestBody BookMarkDto parameter) {

        if (user == null) {
            return JsonResult.fail("접근 권한이 없습니다.");
        }

        parameter.setLoginUserId(user.getId());

//        로그인 유저 추가 로직
//        parameter.setLoginUserId();

        boolean result = bookMarkService.set(parameter);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }
}
