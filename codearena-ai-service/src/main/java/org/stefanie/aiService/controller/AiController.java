package org.stefanie.aiService.controller;


import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.stefanie.aiService.app.AiJudgeApp;
import org.stefanie.aiService.app.LoveApp;
import org.stefanie.aiService.common.common.BaseResponse;
import org.stefanie.aiService.entity.AIQuestionJudgeRequest;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/")
public class AiController {
    @Resource
    LoveApp loveApp;
    @Resource
    AiJudgeApp aiJudgeApp;
    @PostMapping("/love")
    public String doChat(@RequestParam  String message,@RequestParam(required = false)  String chatId) {
        return loveApp.doChatWithMysql(message, chatId);
    }
    @PostMapping("/judge")
    public BaseResponse<AiJudgeApp.AIQuestionJudgeResult> doAiJudge(@RequestBody AIQuestionJudgeRequest message, @RequestParam(required = false)  String chatId) {
        return aiJudgeApp.doAiJudgeWithMysql(message, chatId);
    }
    /**
     * 内存流式RAG输出
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/rag", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> doChatWithRAG(@RequestParam String message,
                                      @RequestParam(required = false) String chatId) {
        return loveApp.doChatWithRAGStream(message, chatId);
    }
    /**
     * 百炼平台流式RAG输出
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/ragCloud", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String doChatWithRAGCloud(@RequestParam String message,
                                      @RequestParam(required = false) String chatId) {
        return loveApp.doChatWithRagCloud(message, chatId);
    }

}

