package org.stefanie.questionService.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.stefanie.questionService.config.RabbitMqConfig;

import javax.annotation.Resource;

@Component
public class QuestionMqProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.QUESTION_EXCHANGE_NAME, RabbitMqConfig.QUESTION_ROUTING_KEY, message);
    }
}
