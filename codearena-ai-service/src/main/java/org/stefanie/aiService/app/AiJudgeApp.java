package org.stefanie.aiService.app;

import cn.hutool.json.JSONUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;
import org.stefanie.aiService.advisor.MyLoggerAdvisor;
import org.stefanie.aiService.common.common.BaseResponse;
import org.stefanie.aiService.common.common.ResultUtils;
import org.stefanie.aiService.entity.AIQuestionJudgeRequest;
import org.stefanie.aiService.entity.AIQuestionJudgeResult;
import org.stefanie.aiService.entity.Conversation;
import org.stefanie.aiService.mapper.ConversationMapper;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;
import static org.stefanie.aiService.prompt.JudgePrompt.JUDGE_PROMPT;

@Component
@Slf4j
public class AiJudgeApp {

    private ChatClient chatClient;
    @Resource
    private ConversationMapper conversationMapper;
    @Resource
    private ChatMemory MysqlBasedChatMemory;
    private final ChatModel dashscopeChatModel;


    private static final String SYSTEM_PROMPT = "你是一个乐于助人的AI";

    /**
     * 初始化 ChatClient
     *
     * @param dashscopeChatModel
     */
    public AiJudgeApp(ChatModel dashscopeChatModel) {
        this.dashscopeChatModel = dashscopeChatModel;
    }

    @PostConstruct
    public void initChatClient() {
        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(MysqlBasedChatMemory),
                        new MyLoggerAdvisor()
                )
                .build();
    }

    /**
     * AI 基础对话（支持多轮对话记忆）
     *
     * @param message
     * @param chatId
     * @return
     */
    public String doChat(String message, String chatId) {
        ChatResponse chatResponse = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }

    /**
     * AI 基础对话（支持多轮对话记忆,持久化数据库）开启新对话的时候
     *
     * @param message
     * @return
     */
    public String doChatWithMysql(String message, String chatId) {
        if (chatId == null) {
            chatId = getFirstConversationID();
        }
        String finalChatId = chatId;
        ChatResponse chatResponse = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, finalChatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }

    public String getFirstConversationID() {
        Conversation conversation = new Conversation();
        conversation.setConversationId(UUID.randomUUID().toString());
        conversationMapper.insert(conversation);
        return conversation.getConversationId();
    }
    public record AIQuestionJudgeResult(
            boolean isPass,
            String reason,       // 为什么错误
            String suggestion,   // 怎么改
            String annotatedCode // 高亮或带注释的用户代码
    ) {}

    /**
     * AI判断
     * @param message
     * @param chatId
     * @return
     */
    public BaseResponse<AIQuestionJudgeResult> doAiJudgeWithMysql(AIQuestionJudgeRequest message, String chatId) {
        if (chatId == null) {
            chatId = getFirstConversationID();
        }
        String finalChatId = chatId;
        String jsonStr = generateString(message);

        AIQuestionJudgeResult aiQuestionJudgeResult = chatClient
                .prompt()
                .system(JUDGE_PROMPT)
                .user(jsonStr)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, finalChatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .entity(AIQuestionJudgeResult.class);
        log.info("loveReport: {}", aiQuestionJudgeResult);
        return ResultUtils.success(aiQuestionJudgeResult);
    }
    public String generateString(AIQuestionJudgeRequest message){
        return "题目标题" + message.getTitle() +","+ "题目描述" + message.getContent() +","+"代码" + message.getCode()+","+"判题用例" + message.getJudgeCase()+","+"判题配置" + message.getJudgeConfig();
    }

    record LoveReport(String title, List<String> suggestions) {

    }

    /**
     * AI 恋爱报告功能（实战结构化输出）
     *
     * @param message
     * @param chatId
     * @return
     */
    public LoveReport doChatWithReport(String message, String chatId) {
        LoveReport loveReport = chatClient
                .prompt()
                .system(SYSTEM_PROMPT + "每次对话后都要生成恋爱结果，标题为{用户名}的恋爱报告，内容为建议列表")
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .entity(LoveReport.class);
        log.info("loveReport: {}", loveReport);
        return loveReport;
    }

    @Resource
    VectorStore loveAppVectorStore;

    public String doChatWithRAG(String message, String chatId) {
        if (chatId == null) {
            chatId = getFirstConversationID();
        }
        String finalChatId = chatId;
        ChatResponse chatResponse = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, finalChatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .advisors(new QuestionAnswerAdvisor(loveAppVectorStore))
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }

    public Flux<String> doChatWithRAGStream(String message, String chatId) {
        if (chatId == null) {
            chatId = getFirstConversationID();
        }
        String finalChatId = chatId;

        // 获取流式数据并拼接为一个字符串
        return chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, finalChatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .advisors(new QuestionAnswerAdvisor(loveAppVectorStore))
                .stream().content()
                .flatMapSequential(Flux::just);
    }
    @Resource
    private Advisor loveAppRagCloudAdvisor;
    @Resource
    private Advisor loveListRagCloudAdvisor;
    public String doChatWithRagCloud(String message, String chatId) {
        if (chatId == null) {
            chatId = getFirstConversationID();
        }
        String finalChatId = chatId;
        ChatResponse chatResponse = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, finalChatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                // 开启日志，便于观察效果
                .advisors(new MyLoggerAdvisor())
                // 应用增强检索服务（云知识库服务）
                .advisors(loveListRagCloudAdvisor)
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }



}
