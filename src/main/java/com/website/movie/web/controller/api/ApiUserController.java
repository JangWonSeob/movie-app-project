package com.website.movie.web.controller.api;

import com.website.movie.biz.dao.AuthorityDao;
import com.website.movie.biz.dao.UserDao;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiUserController {


    private final UserService userService;
    private final UserDao userDao;
    private final AuthorityDao authorityDao;

    @PostMapping("/api/user/set.api")
    @ApiOperation(value = "유저 회원가입 및 정보수정 API", notes = "유저 회원가입 및 정보수정이 가능합니다.")
    public JsonResult set(@RequestBody UserDto user) {

        boolean result = userService.set(user);
        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

    @PostMapping("/api/user/get.api")
    @ApiOperation(value = "유저 한명 조회 API", notes = "유저 한명에 대한 조회 가능합니다.")
    public JsonResult get(@RequestBody UserDto user) {

        UserDto result = userService.get(user);

        if(result == null) {
            return JsonResult.fail(" 입력 값이 옳지 않습니다. ");
        }

        return JsonResult.success(result);
    }

//    @PostMapping("/api/user/gets.api")
//    @ApiOperation(value = "유저 리스트 조회 API", notes = "유저 리스트 조회가 가능합니다.")
//    public JsonResult gets(@RequestBody UserDto user) {
//
//        List<UserDto> result = userService.gets(user);
//
//        return JsonResult.success(result);
//    }

    @PostMapping("/api/user/delete.api")
    @ApiOperation(value = "유저 삭제 API", notes = "해당 유저를 삭제합니다.")
    public JsonResult delete(@RequestBody UserDto user) {

        boolean result = userService.delete(user);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

    //email에 맞게 권한 반환
    @PostMapping("/api/user/userAuth/{email}")
    @ApiOperation(value = "유저 권한 반환 API", notes = "email을 받으면 해당유저의 권한들을 반환합니다.")
    public List<String> user_auth(@PathVariable String email) {
        return authorityDao.selectByEmail(email);
    }

    //email Check axios로 이용
    @PostMapping("/api/user/emailCheck.api")
    public int emailCheck(UserDto user) {
        int result = userService.emailCheck(user);
        return result;
    }
    //
    @PostMapping("/api/iser/nickCheck.api")
    public int nickCheck(UserDto user) {
        int result = userService.nicknameCheck(user);
        return result;
    }

}
