package org.stefanie.questionService.rpc;

import model.entity.Question;
import org.apache.dubbo.config.annotation.DubboService;
import org.stefanie.questionService.service.QuestionService;
import org.stefanie.serviceClient.QuestionRpcService;

import javax.annotation.Resource;

@DubboService(group = "dubbo-group")
public class QuestionRpcServiceImpl implements QuestionRpcService {

    @Resource
    private QuestionService questionService;

    @Override
    public Question getQuestionById(long questionId) {
        return questionService.getById(questionId);
    }
}
