package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.CommentDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.input.CommentInputModel;
import com.website.movie.biz.model.search.CommentSearchModel;
import com.website.movie.biz.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/api/comment/set.api")
    @ApiOperation(value = "댓글 등록 및 수정 API", notes = "댓글 등록 및 수정이 가능합니다.")
    public JsonResult set(@RequestBody CommentInputModel model) {

        boolean result = commentService.set(model);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

    @PostMapping("/api/comment/gets.api")
    @ApiOperation(value = "댓글 리스트 조회 API", notes = "댓글 리스트 조회가 가능합니다.")
    public JsonResult gets(@RequestBody CommentSearchModel model) {

        model.initPage();

        List<CommentDto> list = commentService.gets(model);
        int totalCount = commentService.totalCount(model);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }

    @PostMapping("/api/comment/delete.api")
    @ApiOperation(value = "댓글 삭제 API", notes = "댓글을 삭제합니다.")
    public JsonResult delete(@RequestBody CommentInputModel model) {

        boolean result = commentService.delete(model);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

}
