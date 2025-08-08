package org.stefanie.aiService.common.model.dto.questionBankQuestion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.stefanie.aiService.common.common.PageRequest;

import java.io.Serializable;

/**
 * 查询题库题目关联请求
 *

 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionBankQuestionQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long notId;

    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 创建用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}