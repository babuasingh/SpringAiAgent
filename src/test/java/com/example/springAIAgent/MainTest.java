package com.example.springAIAgent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMainMethodRuns() {
        // Given
        String[] args = {};

        // When & Then - main method should execute without throwing exception
        assertDoesNotThrow(() -> Main.main(args));
    }

    @Test
    void testUserDirectoryPropertyExists() {
        // Given & When
        String userDir = System.getProperty("user.dir");

        // Then
        assertNotNull(userDir);
        assertFalse(userDir.isEmpty());
    }
}
