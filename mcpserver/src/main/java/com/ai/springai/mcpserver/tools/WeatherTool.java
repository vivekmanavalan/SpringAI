package com.ai.springai.mcpserver.tools;

import com.ai.springai.mcpserver.model.Weather;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

@Service
public class WeatherTool {
    @McpTool(name = "get-weather", description = "Tool to get weather conditions for a place")
    public Weather getWeather(
            @McpToolParam(description = "Name of the place to fetch weather details", required = true)
            String place){

        return new Weather(place, "Thunderstorms", "23C");

    }
}
