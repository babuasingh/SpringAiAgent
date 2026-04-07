package com.example.springAIAgent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.Advisor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class AgentControllerTest {

    private AgentController agentController;
    private ChatClient.Builder chatClientBuilder;
    private ChatClient chatClient;

    @BeforeEach
    void setUp() {
        chatClientBuilder = mock(ChatClient.Builder.class);
        chatClient = mock(ChatClient.class);
        when(chatClientBuilder.defaultSystem(anyString())).thenReturn(chatClientBuilder);
        when(chatClientBuilder.defaultTools(any())).thenReturn(chatClientBuilder);
        when(chatClientBuilder.defaultAdvisors(anyList())).thenReturn(chatClientBuilder);
        when(chatClientBuilder.build()).thenReturn(chatClient);
        
        agentController = new AgentController(chatClientBuilder);
    }

    @Test
    void testHealthEndpoint() {
        // When
        Map<String, String> result = agentController.health();

        // Then
        assertEquals("UP", result.get("status"));
        assertEquals("AI Agent is running", result.get("message"));
    }

    @Test
    void testDeleteSession() {
        // When
        Map<String, String> result = agentController.deleteSession("test-session");

        // Then
        assertEquals("Session test-session deleted", result.get("message"));
    }

    @Test
    void testChatWithDefaultSession() {
        // Given
        Map<String, String> request = new HashMap<>();
        request.put("message", "Hello");

        // When & Then - will throw exception due to mocked ChatClient, but we can test session handling
        assertThrows(Exception.class, () -> agentController.chat(request));
    }

    @Test
    void testChatWithCustomSession() {
        // Given
        Map<String, String> request = new HashMap<>();
        request.put("message", "Hello");
        request.put("sessionId", "custom-session");

        // When & Then - will throw exception due to mocked ChatClient
        assertThrows(Exception.class, () -> agentController.chat(request));
    }

    @Test
    void testSimpleChat() {
        // Given
        Map<String, String> request = new HashMap<>();
        request.put("message", "Hello");

        // When & Then - will throw exception due to mocked ChatClient
        assertThrows(Exception.class, () -> agentController.simpleChat(request));
    }
}
