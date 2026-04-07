# SpringAiAgent

A Spring Boot application that provides an AI-powered coding assistant via REST API, integrated with Ollama for language models. This agent can assist with coding tasks, file operations, and command execution through conversational interfaces.

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
- Ollama installed and running (with a compatible model, e.g., llama2)

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

3. Pull a model (if not already done):
   ```bash
   ollama pull llama2
   ```

4. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## API Endpoints

### Chat Endpoints
- `POST /api/agent/chat` - Interactive chat with session persistence
  - Body: `{"message": "Your query", "sessionId": "optional-session-id"}`
- `POST /api/agent/chat/simple` - Simple chat without session persistence
  - Body: `{"message": "Your query"}`

### Utility Endpoints
- `GET /api/agent/health` - Health check
- `DELETE /api/agent/session/{sessionId}` - Delete a chat session

### Basic Endpoint
- `GET /hello` - Returns "Hello World!"

## Usage

Send POST requests to the chat endpoints with your coding queries. The AI agent has access to tools for:
- Reading and editing files
- Running shell commands
- Searching codebases
- Getting current date/time

Example curl request:
```bash
curl -X POST http://localhost:8080/api/agent/chat/simple \
  -H "Content-Type: application/json" \
  -d '{"message": "Help me create a new Java class"}'
```

## Skills

The application includes a skills system in the `skills/` directory:
- **report-writing-skill**: For generating reports and documentation
- **spring-boot-skill**: For Spring Boot code generation and scaffolding

## Configuration

Configuration can be modified in `src/main/resources/application.properties`.

## Testing

Run tests with:
```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

babuasingh
