package com.ai.springaibasics.config;

import org.springframework.ai.chat.cache.semantic.SemanticCache;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.redis.cache.semantic.DefaultSemanticCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.RedisClient;

@Configuration
public class SemanticCacheConfig {

    @Bean
    RedisClient redisClient(@Value("${spring.data.redis.host:localhost}")
                            String host,
                            @Value("${spring.data.redis.port:6379}")
                            int port) {
        return RedisClient.builder()
                .hostAndPort(host, port)
                .build();
    }

    @Bean
    SemanticCache semanticCache( EmbeddingModel embeddingModel, RedisClient redisClient) {
        return DefaultSemanticCache.builder()
                .embeddingModel(embeddingModel)
                .similarityThreshold(0.9)
                .jedisClient(redisClient)
                .build();
    }

    @Bean
    SemanticCacheAdvisor semanticCacheAdvisor(SemanticCache semanticCache) {
        return SemanticCacheAdvisor.builder()
                .cache(semanticCache)
                .build();
    }
}
