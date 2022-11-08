package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.CommentDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.CommentInputModel;
import com.website.movie.biz.model.search.CommentSearchModel;
import com.website.movie.biz.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiCommentController {

    private final CommentService commentService;

    @PostMapping("/api/comment/board/set.api")
    @ApiOperation(value = "게시글 댓글 등록 및 수정 API", notes = "게시글 댓글 등록 및 수정이 가능합니다.")
    public JsonResult setBoard(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {

        if (user == null) {
            return JsonResult.fail("접근 권한이 없습니다.");
        }

        parameter.setLoginUserId(user.getId());
        parameter.setTableName(CommentDto.TABLE_NAME_BOARD);

        boolean result = commentService.set(parameter);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

    @PostMapping("/api/comment/movie/set.api")
    @ApiOperation(value = "영화 댓글 등록 및 수정 API", notes = "영화 댓글 등록 및 수정이 가능합니다.")
    public JsonResult setMovie(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {

        if (user == null) {
            return JsonResult.fail("접근 권한이 없습니다.");
        }

        parameter.setLoginUserId(user.getId());
        parameter.setTableName(CommentDto.TABLE_NAME_MOVIE);

        boolean result = commentService.set(parameter);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

    @PostMapping("/api/comment/board/gets.api")
    @ApiOperation(value = "게시글 댓글 리스트 조회 API", notes = "게시글 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsBoard(@RequestBody CommentDto parameter) {

        parameter.initPage();
        parameter.setSearchType("PARENTS");
        parameter.setTableName(CommentDto.TABLE_NAME_BOARD);

        List<CommentDto> list = commentService.gets(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/board/re/gets.api")
    @ApiOperation(value = "게시글 대댓글 리스트 조회 API", notes = "게시글 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsReBoard(@RequestBody CommentDto parameter) {

        parameter.initPage();
        parameter.setSearchType("CHILD");
        parameter.setTableName(CommentDto.TABLE_NAME_BOARD);

        List<CommentDto> list = commentService.gets(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/movie/gets.api")
    @ApiOperation(value = "영화 댓글 리스트 조회 API", notes = "영화 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsMovie(@RequestBody CommentDto parameter) {

        parameter.initPage();
        parameter.setSearchType("PARENTS");
        parameter.setTableName(CommentDto.TABLE_NAME_MOVIE);

        List<CommentDto> list = commentService.gets(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/movie/re/gets.api")
    @ApiOperation(value = "영화 대댓글 리스트 조회 API", notes = "영화 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsReMovie(@RequestBody CommentDto parameter) {

        parameter.initPage();
        parameter.setSearchType("CHILD");
        parameter.setTableName(CommentDto.TABLE_NAME_MOVIE);

        List<CommentDto> list = commentService.gets(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/delete.api")
    @ApiOperation(value = "댓글 삭제 API", notes = "댓글을 삭제합니다.")
    public JsonResult delete(@RequestBody CommentDto parameter) {

        boolean result = commentService.delete(parameter);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

}
