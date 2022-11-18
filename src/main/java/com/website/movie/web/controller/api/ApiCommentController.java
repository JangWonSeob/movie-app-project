package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.CommentDto;
import com.website.movie.biz.dto.UserDto;
import com.website.movie.biz.model.JsonResult;
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

    @PostMapping("/api/comment/tv/set.api")
    @ApiOperation(value = "영화 댓글 등록 및 수정 API", notes = "영화 댓글 등록 및 수정이 가능합니다.")
    public JsonResult setTv(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {

        if (user == null) {
            return JsonResult.fail("접근 권한이 없습니다.");
        }

        parameter.setLoginUserId(user.getId());
        parameter.setTableName(CommentDto.TABLE_NAME_TV);

        boolean result = commentService.set(parameter);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

    @PostMapping("/api/comment/board/gets.api")
    @ApiOperation(value = "게시글 댓글 리스트 조회 API", notes = "게시글 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsBoard(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {

        parameter.initPage();
        parameter.setSearchType("PARENTS");
        parameter.setTableName(CommentDto.TABLE_NAME_BOARD);
        if (user != null && user.getId() > 0) {
            parameter.setLoginUserId(user.getId());
        }

        List<CommentDto> list = commentService.gets(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/board/re/gets.api")
    @ApiOperation(value = "게시글 대댓글 리스트 조회 API", notes = "게시글 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsReBoard(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {

        parameter.setSearchType("CHILD");
        parameter.setTableName(CommentDto.TABLE_NAME_BOARD);
        if (user != null && user.getId() > 0) {
            parameter.setLoginUserId(user.getId());
        }

        List<CommentDto> list = commentService.getAll(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/board/set/get.api")
    @ApiOperation(value = "게시판 수정 댓글 조회 API", notes = "게시판 수정 댓글 조회가 가능합니다.")
    public JsonResult getSetBoard(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {


        if (user == null && user.getId() < 1) {
            return JsonResult.fail(" 접근 권한이 없습니다. ");
        }
        parameter.setTableName(CommentDto.TABLE_NAME_BOARD);
        parameter.setLoginUserId(user.getId());

        CommentDto detail = commentService.get(parameter);

        if (detail == null) {
            return JsonResult.fail(" 해당 댓글을 찾을 수 없습니다. ");
        }

        if (detail.getRegId() != parameter.getLoginUserId()) {
            return JsonResult.fail(" 접근 권한이 없습니다. ");
        }

        return JsonResult.success(detail);
    }

    @PostMapping("/api/comment/movie/gets.api")
    @ApiOperation(value = "영화 댓글 리스트 조회 API", notes = "영화 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsMovie(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {

        parameter.initPage();
        parameter.setSearchType("PARENTS");
        parameter.setTableName(CommentDto.TABLE_NAME_MOVIE);
        if (user != null && user.getId() > 0) {
            parameter.setLoginUserId(user.getId());
        }

        List<CommentDto> list = commentService.gets(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/movie/re/gets.api")
    @ApiOperation(value = "영화 대댓글 리스트 조회 API", notes = "영화 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsReMovie(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {

        parameter.setSearchType("CHILD");
        parameter.setTableName(CommentDto.TABLE_NAME_MOVIE);
        if (user != null && user.getId() > 0) {
            parameter.setLoginUserId(user.getId());
        }

        List<CommentDto> list = commentService.getAll(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/movie/set/get.api")
    @ApiOperation(value = "영화 수정 댓글 조회 API", notes = "영화 수정 댓글 조회가 가능합니다.")
    public JsonResult getSetMovie(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {


        if (user == null && user.getId() < 1) {
            return JsonResult.fail(" 접근 권한이 없습니다. ");
        }
        parameter.setTableName(CommentDto.TABLE_NAME_MOVIE);
        parameter.setLoginUserId(user.getId());

        CommentDto detail = commentService.get(parameter);

        if (detail == null) {
            return JsonResult.fail(" 해당 댓글을 찾을 수 없습니다. ");
        }

        if (detail.getRegId() != parameter.getLoginUserId()) {
            return JsonResult.fail(" 접근 권한이 없습니다. ");
        }

        return JsonResult.success(detail);
    }

    @PostMapping("/api/comment/tv/gets.api")
    @ApiOperation(value = "영화 댓글 리스트 조회 API", notes = "영화 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsTv(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {

        parameter.initPage();
        parameter.setSearchType("PARENTS");
        parameter.setTableName(CommentDto.TABLE_NAME_TV);
        if (user != null && user.getId() > 0) {
            parameter.setLoginUserId(user.getId());
        }

        List<CommentDto> list = commentService.gets(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/tv/re/gets.api")
    @ApiOperation(value = "영화 대댓글 리스트 조회 API", notes = "영화 댓글 리스트 조회가 가능합니다.")
    public JsonResult getsReTv(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {

        parameter.setSearchType("CHILD");
        parameter.setTableName(CommentDto.TABLE_NAME_TV);
        if (user != null && user.getId() > 0) {
            parameter.setLoginUserId(user.getId());
        }

        List<CommentDto> list = commentService.getAll(parameter);
        int totalCount = commentService.totalCount(parameter);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/tv/set/get.api")
    @ApiOperation(value = "영화 수정 댓글 조회 API", notes = "영화 수정 댓글 조회가 가능합니다.")
    public JsonResult getSetTv(@AuthenticationPrincipal UserDto user, @RequestBody CommentDto parameter) {


        if (user == null && user.getId() < 1) {
            return JsonResult.fail(" 접근 권한이 없습니다. ");
        }
        parameter.setTableName(CommentDto.TABLE_NAME_TV);
        parameter.setLoginUserId(user.getId());

        CommentDto detail = commentService.get(parameter);

        if (detail == null) {
            return JsonResult.fail(" 해당 댓글을 찾을 수 없습니다. ");
        }

        if (detail.getRegId() != parameter.getLoginUserId()) {
            return JsonResult.fail(" 접근 권한이 없습니다. ");
        }

        return JsonResult.success(detail);
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
