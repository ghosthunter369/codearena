package org.stefanie.serviceClient;

import model.entity.Question;

/**
 * 题目服务
 *
 */
public interface QuestionRpcService {

    /**
     * 根据 id 获取题目
     * @param questionId
     * @return
     */
    Question getQuestionById(long questionId);
}
