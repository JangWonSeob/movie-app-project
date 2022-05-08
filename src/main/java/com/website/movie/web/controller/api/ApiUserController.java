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

    @PostMapping("/api/user/insertUser.api")
    @ApiOperation(value = "유저 회원가입 API", notes = "유저회원가입 가능")
    public void insertUser(@RequestBody UserInputModel model) {
        userService.insertUser(model);
    }

    @PostMapping("/api/user/getUser.api")
    @ApiOperation(value = "유저 조회 API", notes = "유저조회 가능")
    public JsonResult getUser(UserSearchModel model) {

        UserDto result = userService.getUser(model);
        if (result == null) {
            return JsonResult.fail(" 입력 값이 옳지 않습니다.");
        }

        return JsonResult.success();
    }

}
