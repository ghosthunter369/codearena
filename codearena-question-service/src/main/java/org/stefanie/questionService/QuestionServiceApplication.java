package org.stefanie.questionService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类（项目启动入口） *
 
 */
// todo 如需开启 Redis，须移除 exclude 中的内容
// @SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling
@ServletComponentScan
@MapperScan("org.stefanie.questionService.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class QuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionServiceApplication.class, args);
    }

}
