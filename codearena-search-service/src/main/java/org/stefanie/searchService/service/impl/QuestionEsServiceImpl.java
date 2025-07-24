package org.stefanie.searchService.service.impl;

import model.dto.question.QuestionEsDTO;
import model.dto.question.QuestionQueryRequest;
import org.springframework.stereotype.Service;
import org.stefanie.searchService.repository.QuestionEsRepository;
import org.stefanie.searchService.service.QuestionEsService;

import javax.annotation.Resource;
@Service
public class QuestionEsServiceImpl implements QuestionEsService {
    @Resource
    private QuestionEsRepository questionEsRepository;

    @Override
    public void saveQuestionEsDTO(QuestionEsDTO questionEsDTO) {
        questionEsRepository.save(questionEsDTO);
    }

    public void updateQuestionEsDTO(QuestionEsDTO questionEsDTO) {
        questionEsRepository.save(questionEsDTO);
    }

    @Override
    public void deleteQuestionEsDTO(long id) {
        questionEsRepository.deleteById(id);
    }
}
