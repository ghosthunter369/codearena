package org.stefanie.aiService.entity;

import lombok.Data;

// 请求 AI 简化题目说明、提炼重点
@Data
public class AIQuestionJudgeResult {
    private boolean isPass;
    private String reason;       // 为什么错误
    private String suggestion;   // 怎么改
    private String annotatedCode; // 高亮或带注释的用户代码
}
