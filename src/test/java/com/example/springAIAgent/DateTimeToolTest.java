package com.example.springAIAgent;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeToolTest {

    @Test
    void testGetTodayDate() {
        // Given
        DateTimeTool dateTimeTool = new DateTimeTool();

        // When
        String result = dateTimeTool.getTodayDate();

        // Then
        assertNotNull(result);
        assertEquals(LocalDate.now().toString(), result);
    }

    @Test
    void testGetTodayDateReturnsValidDateFormat() {
        // Given
        DateTimeTool dateTimeTool = new DateTimeTool();

        // When
        String result = dateTimeTool.getTodayDate();

        // Then - should match ISO-8601 format (YYYY-MM-DD)
        assertTrue(result.matches("\\d{4}-\\d{2}-\\d{2}"), 
                "Date should be in YYYY-MM-DD format");
    }

    @Test
    void testGetTodayDateIsConsistent() {
        // Given
        DateTimeTool dateTimeTool = new DateTimeTool();

        // When
        String result1 = dateTimeTool.getTodayDate();
        String result2 = dateTimeTool.getTodayDate();

        // Then
        assertEquals(result1, result2, "Multiple calls should return same date");
    }
}
