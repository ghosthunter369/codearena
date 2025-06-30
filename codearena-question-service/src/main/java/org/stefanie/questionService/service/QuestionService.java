package org.stefanie.questionService.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import model.dto.post.PostQueryRequest;
import model.entity.Post;
import model.entity.Question;
import model.vo.PostVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 帖子服务
 *
 
 */
public interface QuestionService extends IService<Question> {

}
