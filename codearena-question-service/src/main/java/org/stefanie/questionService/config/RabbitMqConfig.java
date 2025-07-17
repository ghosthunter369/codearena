package org.stefanie.questionService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ 配置
 */
@Configuration
public class RabbitMqConfig {

    // 定义交换机
    public static final String QUESTION_EXCHANGE_NAME = "question_exchange";
    // 定义队列
    public static final String QUESTION_QUEUE_NAME = "question_queue";
    // 定义路由键
    public static final String QUESTION_ROUTING_KEY = "question_routingKey";


    @Bean
    public DirectExchange questionExchange() {
        return new DirectExchange(QUESTION_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue questionQueue() {
        return new Queue(QUESTION_QUEUE_NAME, true, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(questionQueue()).to(questionExchange()).with(QUESTION_ROUTING_KEY);
    }
}
