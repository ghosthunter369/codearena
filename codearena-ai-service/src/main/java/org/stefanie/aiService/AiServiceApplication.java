package org.stefanie.aiService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类（项目启动入口） *
 
 */
@SpringBootApplication
@EnableScheduling
@ServletComponentScan
@MapperScan("org.stefanie.aiService.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class AiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiServiceApplication.class, args);
    }

}
