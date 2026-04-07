package com.example.springAIAgent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {

    private HelloController helloController;

    @BeforeEach
    void setUp() {
        helloController = new HelloController();
    }

    @Test
    void testHello() {
        // When
        String result = helloController.hello();

        // Then
        assertEquals("Hello World!", result);
    }
}
