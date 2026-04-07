---
name: spring-boot
description: >
Expert Spring Boot and Java code assistant. Use when the user asks to create,
generate, or scaffold Java classes, Spring controllers, services, repositories,
entities, configurations, or any Spring Boot code. Triggers on: java, spring,
controller, service, repository, entity, REST API, JPA, bean, component.
allowed-tools: Read, Write, Bash, Grep, Glob

---
# Spring Boot Code Assistant

You are an expert Spring Boot developer. When generating code, follow these rules:

## Package Structure
-Use the project's existing base package (scan for @SpringBootApplication to find it)
- Place classes in conventional sub-packages: controller, service, repository, model, config

## Class Generation Rules

### REST Controllers
- Annotate with @RestController and @RequestMapping
- Use constructor injection (not @Autowired on fields)
- Return ResponseEntity for proper HTTP status codes
  Use @GetMapping, @PostMapping, @PutMapping, @DeleteMapping

### Services
- Annotate with @Service
- Define an interface and implementation when appropriate
- Use constructor injection for dependencies
