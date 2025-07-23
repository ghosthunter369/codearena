package org.stefanie.searchService.service.impl;

import model.dto.question.QuestionEsDTO;
import org.springframework.stereotype.Service;
import org.stefanie.searchService.repository.PostEsRepository;
import org.stefanie.searchService.repository.QuestionEsRepository;
import org.stefanie.searchService.service.QuestionEsService;

import javax.annotation.Resource;
@Service
public class QuestionEsServiceImpl implements QuestionEsService {
    @Resource
    private QuestionEsRepository questionEsRepository;

    @Override
    public int saveQuestionEsDTO(QuestionEsDTO questionEsDTO) {
        questionEsRepository.save(questionEsDTO);
        return 0;
    }

    @Override
    public void deleteQuestionEsDTO(long id) {
        questionEsRepository.deleteById(id);
    }
}
