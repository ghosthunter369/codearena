package org.stefanie.aiService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 帖子接口
 *
 
 */
@RestController
@RequestMapping("/")
@Slf4j
public class AiController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
