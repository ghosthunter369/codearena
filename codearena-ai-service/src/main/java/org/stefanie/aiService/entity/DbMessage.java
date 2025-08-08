package org.stefanie.aiService.entity;

import lombok.Data;
import org.springframework.ai.chat.messages.MessageType;

import java.util.Map;

@Data
public class DbMessage {

    private MessageType messageType;
    private String text;
    private Map<String, Object> metadata;
}

