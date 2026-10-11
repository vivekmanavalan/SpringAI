package com.ai.springaibasics.service;

import com.ai.springaibasics.model.LLMRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SemanticCacheService {

    private final ChatClient chatClient;

    public SemanticCacheService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String getSemanticCacheLLMOutput(LLMRequest llmRequest) {
        return chatClient
                .prompt()
                .system("respond in one line")
                .user(llmRequest.getQuestion())
                .call()
                .content();
    }
}
