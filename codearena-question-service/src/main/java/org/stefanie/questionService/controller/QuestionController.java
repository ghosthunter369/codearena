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
@RequestMapping("/post")
@Slf4j
public class QuestionController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
