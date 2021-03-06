package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiBoardController {

    private final BoardService boardService;

    @PostMapping("/api/board/set.api")
    @ApiOperation(value = "게시판 등록 및 수정 API", notes = "게시판 등록 및 수정이 가능합니다.")
    public JsonResult set(@AuthenticationPrincipal UserDto user, @RequestBody BoardDto parameter) {

        if (user == null) {
            return JsonResult.fail("접근 권한이 없습니다.");
        }

        parameter.setLoginUserId(user.getId());

        boolean result = boardService.set(parameter);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

    @PostMapping("/api/board/get.api")
    @ApiOperation(value = "게시판 한 건 조회 API", notes = "게시판 한 건에 대해서 조회 가능합니다.")
    public JsonResult get(@RequestBody BoardDto parameter) {

        BoardDto result = boardService.get(parameter);

        if(result == null) {
            return JsonResult.fail(" 입력 값이 옳지 않습니다. ");
        }

        return JsonResult.success(result);
    }

    @PostMapping("/api/board/gets.api")
    @ApiOperation(value = "게시판 리스트 조회 API", notes = "게시판 리스트 조회가 가능합니다.")
    public JsonResult gets(@RequestBody BoardDto parameter) {

        parameter.initPage();

        int totalCount = boardService.totalCount(parameter);
        List<BoardDto> list = boardService.gets(parameter, totalCount);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/board/viewcount/add.api")
    @ApiOperation(value = "게시판 조회수 증가 API", notes = "게시판 조회수를 증가시킵니다.")
    public void viewCountUp(@RequestBody BoardDto model) {

        boardService.viewCountUp(model);
    }

    @PostMapping("/api/board/delete.api")
    @ApiOperation(value = "게시판 삭제 API", notes = "게시판을 삭제합니다.")
    public JsonResult delete(@RequestBody BoardDto model) {

        boolean result = boardService.delete(model);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }
}
