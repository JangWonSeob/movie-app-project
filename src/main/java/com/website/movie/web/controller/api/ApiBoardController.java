package com.website.movie.web.controller.api;

import com.website.movie.biz.dto.BoardDto;
import com.website.movie.biz.model.JsonResult;
import com.website.movie.biz.model.input.BoardInputModel;
import com.website.movie.biz.model.search.BoardSearchModel;
import com.website.movie.biz.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiBoardController {

    private final BoardService boardService;

    @PostMapping("/api/board/set.api")
    public JsonResult set(@RequestBody BoardInputModel model) {

        boolean result = boardService.set(model);

        if(!result) {
            return JsonResult.fail(" 데이터 처리 중 문제가 발생하였습니다. ");
        }

        return JsonResult.success();
    }

    @PostMapping("/api/board/get.api")
    public JsonResult get(@RequestBody BoardSearchModel model) {

        BoardDto result = boardService.get(model);

        if(result == null) {
            return JsonResult.fail(" 입력 값이 옳지 않습니다. ");
        }

        return JsonResult.success();
    }

    @PostMapping("/api/board/gets.api")
    public JsonResult gets(@RequestBody BoardSearchModel model) {

        List<BoardDto> list = boardService.gets(model);
        int totalCount = boardService.totalCount(model);

        Map<String, Object> result = new HashMap<>();

        result.put("list", list);
        result.put("totalCount", totalCount);

        return JsonResult.success(result);
    }


}
