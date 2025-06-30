package org.stefanie.searchService.controller;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.stefanie.searchService.service.PostService;

import javax.annotation.Resource;

/**
 * 帖子接口
 *
 
 */
@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
