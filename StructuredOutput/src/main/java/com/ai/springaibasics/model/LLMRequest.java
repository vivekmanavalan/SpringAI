package com.ai.springaibasics.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LLMRequest {
    private String question;

}
