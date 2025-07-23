package org.stefanie.searchService.repository;

import model.dto.post.PostEsDTO;
import model.dto.question.QuestionEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 问题 ES 操作
 *
 */
public interface QuestionEsRepository extends ElasticsearchRepository<QuestionEsDTO, Long> {
}
