package org.stefanie.searchService.service.impl;

import model.dto.post.PostEsDTO;
import org.springframework.stereotype.Service;
import org.stefanie.searchService.repository.PostEsRepository;
import org.stefanie.searchService.service.PostEsService;

import javax.annotation.Resource;

/**
 * 帖子 ES 服务实现
 *
 */
@Service
public class PostEsServiceImpl implements PostEsService {

    @Resource
    private PostEsRepository postEsRepository;

    @Override
    public int savePostEsDTO(PostEsDTO postEsDTO) {
        postEsRepository.save(postEsDTO);
        return 0;
    }

    @Override
    public void deletePostEsDTO(long id) {
        postEsRepository.deleteById(id);
    }
}
