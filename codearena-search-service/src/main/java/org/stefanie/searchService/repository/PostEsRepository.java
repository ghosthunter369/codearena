package org.stefanie.searchService.repository;

import model.dto.post.PostEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 帖子 ES 操作
 *
 */
public interface PostEsRepository extends ElasticsearchRepository<PostEsDTO, Long> {
}
