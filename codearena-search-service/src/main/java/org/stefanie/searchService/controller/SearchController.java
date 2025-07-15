package org.stefanie.searchService.controller;

import cn.hutool.core.util.ObjectUtil;
import common.BaseResponse;
import common.ErrorCode;
import common.ResultUtils;
import exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import model.dto.question.QuestionAddRequest;
import model.dto.question.QuestionQueryRequest;
import model.entity.Question;
import org.springframework.web.bind.annotation.*;
import org.stefanie.searchService.service.SearchService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 搜索服务接口
 *
 */
@RestController
@Slf4j
public class SearchController {

    @Resource
    private SearchService searchService;

    /**
     * 创建题目（ES 同步）
     *
     * @param question
     * @return
     */
    @PostMapping("/save/question")
    public BaseResponse<Question> saveQuestion(@RequestBody Question question) {
        if (question == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Question result = searchService.saveQuestion(question);
        return ResultUtils.success(result);
    }

    /**
     * 搜索题目
     *
     * @param question
     * @return
     */
    @PostMapping("/search/question")
    public BaseResponse<List<Question>> searchQuestion(@RequestBody QuestionQueryRequest question) {
        if (question == null || ObjectUtil.isEmpty(question)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<Question> result = searchService.searchQuestion(question);
        return ResultUtils.success(result);
    }
}