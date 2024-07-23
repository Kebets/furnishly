package com.ast.furnishly.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class FurnitureTest {

    private Furniture furniture;

    @BeforeEach
    void setUp() {
        furniture = new Furniture();
    }

    @Test
    void testId() {
        Long expectedId = 1L;
        furniture.setId(expectedId);

        assertEquals(expectedId, furniture.getId());
    }

    @Test
    void testType() {
        Type expectedType = new Type();
        furniture.setType(expectedType);

        assertEquals(expectedType, furniture.getType());
    }

    @Test
    void testName() {
        String expectedName = "Chair";
        furniture.setName(expectedName);

        assertEquals(expectedName, furniture.getName());
    }

    @Test
    void testManufacturer() {
        Manufacturer expectedManufacturer = new Manufacturer();
        furniture.setManufacturer(expectedManufacturer);

        assertEquals(expectedManufacturer, furniture.getManufacturer());
    }

    @Test
    void testPrice() {
        BigDecimal expectedPrice = new BigDecimal("199.99");
        furniture.setPrice(expectedPrice);

        assertEquals(expectedPrice, furniture.getPrice());
    }

    @Test
    void testQuantity() {
        int expectedQuantity = 10;
        furniture.setQuantity(expectedQuantity);

        assertEquals(expectedQuantity, furniture.getQuantity());
    }
}
