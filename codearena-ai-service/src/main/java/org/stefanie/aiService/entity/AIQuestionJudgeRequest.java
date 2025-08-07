package org.stefanie.aiService.entity;

import lombok.Data;

// 请求 AI 简化题目说明、提炼重点
@Data
public class AIQuestionJudgeRequest {
    private Long questionId;
    private String title;     // 原题标题
    private String content; // 原题描述
    private String code;
    private String judgeCase; //判题案例
    private String judgeConfig; //判题配置
}
