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

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class ApiBookMarkController {

    private final BookMarkService bookMarkService;

    @PostMapping("/api/book-mark/board/set.api")
    @ApiOperation(value = "게시판 즐겨찾기 등록 및 삭제 API", notes = "게시판 즐겨찾기 등록 및 삭제가 가능합니다.")
    public JsonResult boardSet(@AuthenticationPrincipal UserDto user, @RequestBody BookMarkDto parameter) {

        if (user == null) {
            return JsonResult.fail("로그인 후 이용해주세요.");
//            return JsonResult.fail("접근 권한이 없습니다.");
        }

        parameter.setLoginUserId(user.getId());

        System.out.println("bookmark parameter.getLoginUserId()");
        System.out.println(parameter.getLoginUserId());

//        로그인 유저 추가 로직
//        parameter.setLoginUserId();
        System.out.println("BookMark parameter");
        System.out.println(parameter);

        parameter.setTableName(BookMarkDto.TABLE_NAME_BOARD);
        boolean bookMarkYn = bookMarkService.set(parameter);
        HashMap<String, Boolean> result = new HashMap<>();
        result.put("bookMarkYn", bookMarkYn);

        System.out.println("BookMark parameter");
        System.out.println(parameter);

        return JsonResult.success(result);
    }

    @PostMapping("/api/book-mark/movie/set.api")
    @ApiOperation(value = "영화 즐겨찾기 등록 및 삭제 API", notes = "영화 즐겨찾기 등록 및 삭제가 가능합니다.")
    public JsonResult MovieSet(@AuthenticationPrincipal UserDto user, @RequestBody BookMarkDto parameter) {

        if (user == null) {
            return JsonResult.fail("로그인 후 이용해주세요.");
//            return JsonResult.fail("접근 권한이 없습니다.");
        }

        parameter.setLoginUserId(user.getId());

//        로그인 유저 추가 로직
//        parameter.setLoginUserId();

        parameter.setTableName(BookMarkDto.TABLE_NAME_MOVIE);
        boolean bookMarkYn = bookMarkService.set(parameter);
        HashMap<String, Boolean> result = new HashMap<>();
        result.put("bookMarkYn", bookMarkYn);
        return JsonResult.success(result);
    }
}
