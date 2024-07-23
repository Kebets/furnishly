package com.ast.furnishly.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManufacturerTest {

    private Manufacturer manufacturer;

    @BeforeEach
    void setUp() {
        manufacturer = new Manufacturer();
    }

    @Test
    void testId() {
        Long expectedId = 1L;
        manufacturer.setId(expectedId);

        assertEquals(expectedId, manufacturer.getId());
    }

    @Test
    void testName() {
        String expectedName = "ABC Furniture";
        manufacturer.setName(expectedName);

        assertEquals(expectedName, manufacturer.getName());
    }

    @Test
    void testAddress() {
        String expectedAddress = "123 Main Street";
        manufacturer.setAddress(expectedAddress);

        assertEquals(expectedAddress, manufacturer.getAddress());
    }

    @Test
    void testCountry() {
        String expectedCountry = "USA";
        manufacturer.setCountry(expectedCountry);

        assertEquals(expectedCountry, manufacturer.getCountry());
    }
}
