package org.stefanie.searchService.service;

import model.dto.question.QuestionEsDTO;

public interface QuestionEsService {

    /**
     * 保存
     *
     * @param questionEsDTO
     * @return
     */
    int saveQuestionEsDTO(QuestionEsDTO questionEsDTO);

    /**
     * 删除
     *
     * @param id
     */
    void deleteQuestionEsDTO(long id);
}
