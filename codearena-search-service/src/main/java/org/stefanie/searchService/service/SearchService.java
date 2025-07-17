package org.stefanie.searchService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import model.dto.question.QuestionQueryRequest;
import model.entity.Post;
import model.entity.Question;

import java.util.List;


/**
 * 帖子服务
 *
 
 */
public interface SearchService extends IService<Post> {
     Question saveQuestion(Question question);
     List<Question> searchQuestion(QuestionQueryRequest question);
}
