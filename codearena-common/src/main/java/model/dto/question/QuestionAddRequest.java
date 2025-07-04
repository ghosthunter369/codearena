package model.dto.question;

import lombok.Data;
import model.dto.judge.JudgeCase;
import model.dto.judge.JudgeConfig;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请求
 *
 */
@Data
public class QuestionAddRequest implements Serializable {


    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;


    /**
     * 判题用例（json 数组）
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置（json 对象）
     */
    private JudgeConfig judgeConfig;

    /**
     *  题目类型（0-面试题，1-算法题）

     */
    private Integer questionType;

    private static final long serialVersionUID = 1L;
}