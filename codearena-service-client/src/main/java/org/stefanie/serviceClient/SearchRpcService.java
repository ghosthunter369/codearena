package org.stefanie.serviceClient;

import model.dto.question.QuestionQueryRequest;
import model.entity.Question;

import java.util.List;

public interface SearchRpcService {
    /**
     * 根据 id 获取题目
     * @param  questionQueryRequest
     * @return
     */
    List<Question> searchQuestion(QuestionQueryRequest questionQueryRequest);
}
