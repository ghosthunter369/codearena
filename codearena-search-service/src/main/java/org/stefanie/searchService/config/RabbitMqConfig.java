package org.stefanie.searchService.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置
 */
@Configuration
public class RabbitMqConfig {

    // 定义交换机
    public static final String QUESTION_EXCHANGE_NAME = "question_sync_exchange";
    // 定义队列
    public static final String QUESTION_QUEUE_NAME = "question_sync_queue";
    // 定义路由键


    @Bean
    public TopicExchange questionExchange() {
        return new TopicExchange(QUESTION_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue questionQueue() {
        return new Queue(QUESTION_QUEUE_NAME, true, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(questionQueue()).to(questionExchange()).with("question.sync.*");
    }
}
