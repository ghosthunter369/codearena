package org.stefanie.judgeService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 帖子接口
 *
 
 */
@RestController
@RequestMapping("/")
@Slf4j
public class JudgeController {

        @GetMapping("/judge")
        public String judge() {
            return "hello";
        }

}
