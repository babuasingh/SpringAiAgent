package com.example.springAIAgent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "*")
public class AgentController {

    private final ChatClient.Builder chatClientBuilder;
    private final Map<String, ChatClient> clientSessions = new ConcurrentHashMap<>();

    public AgentController(ChatClient.Builder chatClientBuilder) {
        this.chatClientBuilder = chatClientBuilder;
    }

    private ChatClient createClient(String sessionId) {
        return chatClientBuilder
                .defaultSystem("""
                            You are a helpful coding assistant. You have access to tools
                            for reading files, searching code, running shell commands,
                            and editing files. Use them to help the user with their codebase and related queries .
                            Current directory: %s
                        """.formatted(System.getProperty("user.dir")))
                .defaultTools(new DateTimeTool())
                .defaultAdvisors(
                        org.springframework.ai.chat.client.advisor.ToolCallAdvisor.builder()
                                .conversationHistoryEnabled(true)
                                .build()
                )
                .build();
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String query = request.get("message");
        String sessionId = request.getOrDefault("sessionId", "default");

        ChatClient client = clientSessions.computeIfAbsent(sessionId, this::createClient);

        String response = client
                .prompt()
                .user(query)
                .call()
                .content();

        return Map.of("response", response, "sessionId", sessionId);
    }

    @PostMapping("/chat/simple")
    public Map<String, String> simpleChat(@RequestBody Map<String, String> request) {
        String query = request.get("message");

        ChatClient client = chatClientBuilder
                .defaultSystem("""
                            You are a helpful coding assistant. You have access to tools
                            for reading files, searching code, running shell commands,
                            and editing files. Use them to help the user with their codebase and related queries .
                            Current directory: %s
                        """.formatted(System.getProperty("user.dir")))
                .defaultTools(new DateTimeTool())
                .build();

        String response = client
                .prompt()
                .user(query)
                .call()
                .content();

        return Map.of("response", response);
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "message", "AI Agent is running");
    }

    @DeleteMapping("/session/{sessionId}")
    public Map<String, String> deleteSession(@PathVariable String sessionId) {
        clientSessions.remove(sessionId);
        return Map.of("message", "Session " + sessionId + " deleted");
    }
}
