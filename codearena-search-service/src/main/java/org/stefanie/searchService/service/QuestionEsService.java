package org.stefanie.searchService.service;

import model.dto.question.QuestionEsDTO;
import model.dto.question.QuestionQueryRequest;

public interface QuestionEsService {

    /**
     * 保存
     *
     * @param questionEsDTO
     */
    void saveQuestionEsDTO(QuestionEsDTO questionEsDTO);
    /**
     * 更新
     */
    void updateQuestionEsDTO(QuestionEsDTO questionEsDTO);

    /**
     * 删除
     *
     * @param id
     */
    void deleteQuestionEsDTO(long id);
}
