package org.stefanie.searchService.mq;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import model.dto.question.QuestionEsDTO;
import model.entity.Question;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.stefanie.searchService.service.QuestionEsService;
import org.stefanie.serviceClient.QuestionRpcService;

import javax.annotation.Resource;

@Component
@Slf4j
public class QuestionMqConsumer {

    @Resource
    private QuestionEsService questionEsService;

    @DubboReference(group = "dubbo-group")
    private QuestionRpcService questionRpcService;

    @SneakyThrows
    @RabbitListener(queues = {"question_queue"}, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        log.info("receiveMessage message = {}", message);
        if(StringUtils.isEmpty(message)){
            channel.basicReject(deliveryTag, false);
        }
        long questionId = Long.parseLong(message);
        Question question = questionRpcService.getQuestionById(questionId);
        if (question == null) {
            // 如果题目为空，说明已经被删除了
            questionEsService.deleteQuestionEsDTO(questionId);
            channel.basicAck(deliveryTag, false);
            return;
        }
        // 转化为 PostEsDTO
        QuestionEsDTO questionEsDTO = QuestionEsDTO.objToDto(question);
        questionEsService.saveQuestionEsDTO(questionEsDTO);
        channel.basicAck(deliveryTag, false);
    }
}
