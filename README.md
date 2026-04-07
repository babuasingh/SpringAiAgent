# SpringAiAgent

A Spring Boot application that provides a command line interactive AI-powered coding assistant, integrated with Ollama for language models. This agent can assist with coding tasks, file operations, and command execution through conversational interfaces. For this project, I used the qwen3.5:397b-cloud model in Ollama.

## Features

- **AI Chat Interface**: Interactive chat endpoints for coding assistance
- **Tool Integration**: Built-in tools like DateTimeTool for enhanced functionality
- **Session Management**: Persistent chat sessions for continuous conversations
- **Health Monitoring**: Endpoint to check application status
- **Skill System**: Modular skills for specialized tasks (report writing, Spring Boot code generation)

## Technologies Used

- **Spring Boot** 4.0.5
- **Spring AI** 2.0.0-M4
- **Ollama** for language model integration
- **Java** 21
- **Maven** for build management

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Ollama installed and running (with the qwen3.5:397b-cloud model)

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/babuasingh/SpringAiAgent.git
   cd SpringAiAgent
   ```

2. Ensure Ollama is running:
   ```bash
   ollama serve
   ```

3. Pull the model:
   ```bash
   ollama pull qwen3.5:397b-cloud
   ```

4. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## Skills

The application includes a skills system in the `skills/` directory:
- **report-writing-skill**: For generating reports and documentation
- **spring-boot-skill**: For Spring Boot code generation and scaffolding

## Configuration

Configuration can be modified in `src/main/resources/application.properties`.
