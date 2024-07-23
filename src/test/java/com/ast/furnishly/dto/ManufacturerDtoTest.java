package com.ast.furnishly.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ManufacturerDtoTest {

    private ManufacturerDto manufacturerDto;

    @BeforeEach
    void setUp() {
        manufacturerDto = new ManufacturerDto();
    }

    @Test
    void testId() {
        Long expectedId = 1L;
        manufacturerDto.setId(expectedId);

        assertEquals(expectedId, manufacturerDto.getId());
    }

    @Test
    void testName() {
        String expectedName = "ABC Furniture";
        manufacturerDto.setName(expectedName);

        assertEquals(expectedName, manufacturerDto.getName());
    }

    @Test
    void testAddress() {
        String expectedAddress = "123 Main Street";
        manufacturerDto.setAddress(expectedAddress);

        assertEquals(expectedAddress, manufacturerDto.getAddress());
    }

    @Test
    void testCountry() {
        String expectedCountry = "USA";
        manufacturerDto.setCountry(expectedCountry);

        assertEquals(expectedCountry, manufacturerDto.getCountry());
    }

}
