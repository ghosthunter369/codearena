package org.stefanie.questionService.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.stefanie.questionService.config.RabbitMqConfig;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static enums.Question.QuestionMqEnum.QUESTION_ROUTINE;

@Component
public class QuestionMqProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param message
     */
    public void sendMessage(String message,String operation) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("operation", operation);
        payload.put("id", message);
        rabbitTemplate.convertAndSend(RabbitMqConfig.QUESTION_EXCHANGE_NAME,QUESTION_ROUTINE.getValue() + operation , payload);
    }
}
