package org.stefanie.searchService.mapper;

import model.entity.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface QuestionRepository extends ElasticsearchRepository<Question, Long> {
    List<Question> findByTitle(String title);
}