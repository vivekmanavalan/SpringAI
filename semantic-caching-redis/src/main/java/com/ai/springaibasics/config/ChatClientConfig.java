package com.ai.springaibasics.config;

import org.springframework.ai.chat.cache.semantic.SemanticCache;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientBuilderCustomizer;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }

    /*
    * Retains messages in the cache
    * */
    @Bean
    ChatClientBuilderCustomizer chatClientBuilderCustomizer() {
        return chatClientBuilder -> {
            chatClientBuilder.defaultAdvisors(
                    MessageChatMemoryAdvisor.builder(
                                    MessageWindowChatMemory.builder()
                                            .maxMessages(10)
                                            .build())
                            .build());
        };
    }

    /*Intercepts requests and checks for semantically similar responses
     * calls LLM only if there's no match */
    @Bean
    ChatClientBuilderCustomizer addSemanticCache(SemanticCacheAdvisor semanticCacheAdvisor) {
        return chatClientBuilder -> chatClientBuilder.defaultAdvisors(semanticCacheAdvisor);
    }
}
