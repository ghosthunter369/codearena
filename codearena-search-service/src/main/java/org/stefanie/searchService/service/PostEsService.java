package org.stefanie.searchService.service;

import model.dto.post.PostEsDTO;

/**
 * 帖子 ES 服务
 *
 */
public interface PostEsService {

    /**
     * 保存
     *
     * @param postEsDTO
     * @return
     */
    int savePostEsDTO(PostEsDTO postEsDTO);

    /**
     * 删除
     *
     * @param id
     */
    void deletePostEsDTO(long id);
}
