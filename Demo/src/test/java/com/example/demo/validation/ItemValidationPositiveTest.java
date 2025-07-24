// src/test/java/com/example/demo/validation/ItemValidationSimpleTest.java
package com.example.demo.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*; // Import static methods for assertions

class ItemValidationPositiveTest {

    // --- Tests for validateItemName method ---

    @Test
    @DisplayName("validateItemName: Should not throw exception for a valid name")
    void validateItemName_ValidCase() {
        String validName = "My Test Item";
        
        // validate no error return
        assertDoesNotThrow(() -> ItemValidation.validateItemName(validName));
    }

    @Test
    @DisplayName("validateItemName: Should throw RuntimeException for a blank name")
    void validateItemName_InvalidCase_Blank() {
        String blankName = "   ";
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            ItemValidation.validateItemName(blankName);
        });
        assertEquals("Item name cannot be empty or blank.", thrown.getMessage());
    }

    // --- Tests for parseAndValidateLongId method ---

    @Test
    @DisplayName("parseAndValidateLongId: Should return correct Long for a valid numeric string")
    void parseAndValidateLongId_ValidCase() {
        String idString = "456";
        Long expectedId = 456L;
        Long actualId = ItemValidation.parseAndValidateLongId(idString);
        assertEquals(expectedId, actualId);
    }
}