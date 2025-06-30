package org.stefanie.questionService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import model.entity.Question;
import org.springframework.stereotype.Service;
import org.stefanie.questionService.mapper.QuestionMapper;
import org.stefanie.questionService.service.QuestionService;

/**
 * 帖子服务实现
 *
 
 */
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}




