package com.example.springAIAgent;

import org.springaicommunity.agent.tools.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class SpringAiAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiAgentApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ChatClient.Builder chatClientBuilder) {
        return args -> {

            ChatClient client = chatClientBuilder
                    .defaultSystem("""
                                You are a helpful coding assistant. You have access to tools
                                for reading files, searching code, running shell commands,
                                and editing files. Use them to help the user with their codebase and related queries .
                                Current directory: %s
                            """.formatted(System.getProperty("user.dir")))
                    .defaultToolCallbacks(SkillsTool.builder()
                            .addSkillsDirectory("skills") // Specify the directory where your skills are located
                            .build()
                    )
                    .defaultTools(
                            FileSystemTools.builder().build(),  // Provides tools for file system operations like reading and writing files
                            GrepTool.builder().build(),       // Provides tools for searching through files using grep-like functionality ,example "search for 'TODO' in the codebase"
                            GlobTool.builder().build(),    // Provides tools for pattern matching and file searching using glob patterns, example "find all .java files in the src directory"
                            ShellTools.builder().build(), // Provides tools for executing shell commands, example "run 'ls -la' in the current directory"
                            new DateTimeTool()
                    ).defaultAdvisors(
                            ToolCallAdvisor.builder()
                                    .conversationHistoryEnabled(true)
                                    .build(),
                            MessageChatMemoryAdvisor.builder(
                                    MessageWindowChatMemory.builder()
                                            .maxMessages(50)
                                            .build()
                            ).build()
                    )
                    .build();

            System.out.println(" 🤖 Welcome to the Spring AI Agent! Enter your query :");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter your query (or 'exit' to quit):");
                String query = scanner.nextLine();
                if (query.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }
                try {
                    String response = client
                            .prompt(query)
                            .toolContext(Map.of("currentDirectory", System.getProperty("user.dir")))
                            .call()
                            .content();
                    System.out.println("Agent response: " + response);
                } catch (Exception e) {
                    System.out.println("Error processing query: " + e.getMessage());
                }
            }
            // You can add code here to initialize your agent or perform any startup tasks
        };
    }
}
