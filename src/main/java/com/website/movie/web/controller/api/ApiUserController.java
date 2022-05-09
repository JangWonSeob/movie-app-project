package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.UserInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;
import com.website.movie.biz.model.search.UserSearchModel;
import com.website.movie.biz.service.BoardService;
import com.website.movie.biz.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiUserController {

    private final UserService userService;

    @PostMapping("/api/user/set.api")
    @ApiOperation(value = "유저 회원가입 및 정보수정 API", notes = "유저 회원가입 및 정보수정이 가능합니다.")
    public JsonResult set(@RequestBody UserInputModel model) {

        boolean result = userService.set(model);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

    @PostMapping("/api/user/get.api")
    @ApiOperation(value = "유저 한명 조회 API", notes = "유저 한명에 대한 조회 가능합니다.")
    public JsonResult get(@RequestBody UserSearchModel model) {

        UserDto result = userService.get(model);

        if(result == null) {
            return JsonResult.fail(" 입력 값이 옳지 않습니다. ");
        }

        return JsonResult.success(result);
    }

    @PostMapping("/api/user/gets.api")
    @ApiOperation(value = "유저 리스트 조회 API", notes = "유저 리스트 조회가 가능합니다.")
    public JsonResult gets(@RequestBody UserSearchModel model) {

        List<UserDto> result = userService.gets(model);

        return JsonResult.success(result);
    }

    @PostMapping("/api/user/delete.api")
    @ApiOperation(value = "유저 삭제 API", notes = "해당 유저를 삭제합니다.")
    public JsonResult delete(@RequestBody UserInputModel model) {

        boolean result = userService.delete(model);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

}
