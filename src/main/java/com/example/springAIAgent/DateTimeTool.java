package com.example.springAIAgent;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class DateTimeTool {

    @Tool(description = "Get today's date")
    public String getTodayDate() {
        System.out.println("getTodayDate tool called");
        return java.time.LocalDate.now().toString();
    }
}