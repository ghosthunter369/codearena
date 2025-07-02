package org.stefanie.questionService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stefanie.questionService.service.QuestionService;

import javax.annotation.Resource;

/**
 * 帖子接口
 *
 
 */
@RestController
@Slf4j
public class QuestionController {

    @Resource
    private QuestionService questionService;


}
