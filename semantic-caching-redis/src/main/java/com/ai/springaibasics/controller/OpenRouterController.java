package com.ai.springaibasics.controller;

import com.ai.springaibasics.model.BlogList;
import com.ai.springaibasics.model.LLMRequest;
import com.ai.springaibasics.service.OpenRouterService;
import com.ai.springaibasics.service.SemanticCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class OpenRouterController {

    private OpenRouterService openRouterService;
    private SemanticCacheService semanticCacheService;

    public OpenRouterController(OpenRouterService openRouterService, SemanticCacheService semanticCacheService) {
        this.openRouterService = openRouterService;
        this.semanticCacheService = semanticCacheService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return openRouterService.getResponseFromOpenRouter();
    }

    @PostMapping("/ask")
    public String askLLM( @RequestBody LLMRequest request, @CookieValue(name = "X-CONV-ID") String conversationId){
        log.info("Asking LLM {}", request);
        conversationId = conversationId == null ? UUID.randomUUID().toString() : conversationId;
        return openRouterService.askLLMWithChatMemory(request, conversationId);
    }

    @GetMapping("/askspringai/{topic}")
    public BlogList askLLM(@PathVariable String topic){
        log.info("Asking LLM about spring AI {}", topic);
        return openRouterService.getStructuredOutput(topic);
    }

    @PostMapping("/askcache")
    public String askLLMCache(@RequestBody LLMRequest request){
        log.info("Asking LLM {}", request);
        return semanticCacheService.getSemanticCacheLLMOutput(request);
    }
}
