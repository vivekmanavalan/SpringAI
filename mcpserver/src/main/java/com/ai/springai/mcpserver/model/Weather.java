package com.ai.springai.mcpserver.model;

public record Weather(
    String place,
    String weatherCondition,
    String temperature){}
