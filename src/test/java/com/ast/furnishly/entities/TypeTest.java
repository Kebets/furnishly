package com.ast.furnishly.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeTest {

    private Type type;

    @BeforeEach
    void setUp() {
        type = new Type();
    }

    @Test
    void testId() {
        Long expectedId = 1L;
        type.setId(expectedId);

        assertEquals(expectedId, type.getId());
    }

    @Test
    void testName() {
        String expectedName = "Chair";
        type.setName(expectedName);

        assertEquals(expectedName, type.getName());
    }
}
