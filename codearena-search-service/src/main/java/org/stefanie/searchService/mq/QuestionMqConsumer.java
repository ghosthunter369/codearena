package org.stefanie.searchService.mq;

import cn.hutool.core.map.MapUtil;
import com.rabbitmq.client.Channel;
import enums.Question.QuestionMqEnum;
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
import java.util.Map;

import static com.baomidou.mybatisplus.core.assist.ISqlRunner.*;

@Component
@Slf4j
public class QuestionMqConsumer {

    @Resource
    private QuestionEsService questionEsService;

    @DubboReference(group = "dubbo-group")
    private QuestionRpcService questionRpcService;

    @SneakyThrows
    @RabbitListener(queues = {"question_sync_queue"}, ackMode = "MANUAL")
    public void receiveMessage(Map<String, Object> message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        log.info("receiveMessage message = {}", message);
        if(MapUtil.isEmpty(message)){
            channel.basicReject(deliveryTag, false);
        }
        String operation = (String) message.get("operation");
        String id = (String) message.get("id");
        if (StringUtils.isEmpty(operation) || StringUtils.isEmpty(id)) {
            channel.basicReject(deliveryTag, false);
            return;
        }
        long questionId = Long.parseLong(id);
        Question question = questionRpcService.getQuestionById(questionId);
        if (question == null) {
            // 如果题目为空，说明已经被删除了
            questionEsService.deleteQuestionEsDTO(questionId);
            channel.basicAck(deliveryTag, false);
            return;
        }
        // 转化为 QuestionEsDTO
        QuestionEsDTO questionEsDTO = QuestionEsDTO.objToDto(question);
        if (questionEsDTO == null) {
            channel.basicReject(deliveryTag, false);
            return;
        }
        // 根据不同的操作类型，进行不同的操作
        QuestionMqEnum mqEnum = QuestionMqEnum.getEnumByValue(operation);
        if (mqEnum == null) {
            channel.basicReject(deliveryTag, false);
            return;
        }
        switch (mqEnum) {
            case INSERT:
                questionEsService.saveQuestionEsDTO(questionEsDTO);
                break;
            case UPDATE:
                questionEsService.updateQuestionEsDTO(questionEsDTO);
                break;
            case DELETE:
                questionEsService.deleteQuestionEsDTO(questionId);
                break;
            default:
                log.warn("Unsupported operation: " + operation);
        }
        channel.basicAck(deliveryTag, false);
    }

}
