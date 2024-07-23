package com.ast.furnishly.dto;

import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.entities.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class FurnitureDtoTest {

    private FurnitureDto furnitureDto;

    @BeforeEach
    void setUp() {
        furnitureDto = new FurnitureDto();
    }

    @Test
    void testId() {
        Long expectedId = 1L;
        furnitureDto.setId(expectedId);

        assertEquals(expectedId, furnitureDto.getId());
    }

    @Test
    void testType() {
        Type expectedType = new Type();
        furnitureDto.setType(expectedType);

        assertEquals(expectedType, furnitureDto.getType());
    }

    @Test
    void testName() {
        String expectedName = "Chair";
        furnitureDto.setName(expectedName);

        assertEquals(expectedName, furnitureDto.getName());
    }

    @Test
    void testManufacturer() {
        Manufacturer expectedManufacturer = new Manufacturer();
        furnitureDto.setManufacturer(expectedManufacturer);

        assertEquals(expectedManufacturer, furnitureDto.getManufacturer());
    }

    @Test
    void testPrice() {
        BigDecimal expectedPrice = new BigDecimal("199.99");
        furnitureDto.setPrice(expectedPrice);

        assertEquals(expectedPrice, furnitureDto.getPrice());
    }

    @Test
    void testQuantity() {
        int expectedQuantity = 10;
        furnitureDto.setQuantity(expectedQuantity);

        assertEquals(expectedQuantity, furnitureDto.getQuantity());
    }
}
