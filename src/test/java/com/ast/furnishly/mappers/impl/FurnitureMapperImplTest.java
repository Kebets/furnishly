package com.ast.furnishly.mappers.impl;

import com.ast.furnishly.dto.FurnitureDto;
import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.entities.Furniture;
import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.entities.Type;
import com.ast.furnishly.mappers.FurnitureMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FurnitureMapperImplTest {
    private final FurnitureMapper furnitureMapper = FurnitureMapperImpl.getInstance();

    @Test
    public void testMapFurnitureToFurnitureDto() {
        Furniture furniture = new Furniture(1L, new Type(10L, "Chair"), "Wooden Chair",
                new Manufacturer(100L, "ABC Furniture", "123 Main St", "USA"),
                BigDecimal.valueOf(100.0), 5);
        FurnitureDto furnitureDto = furnitureMapper.map(furniture);

        assertEquals(1L, furnitureDto.getId(), "Id should match");
        assertEquals("Chair", furnitureDto.getType().getName(), "Type name should match");
        assertEquals("Wooden Chair", furnitureDto.getName(), "Name should match");
        assertEquals("ABC Furniture", furnitureDto.getManufacturer().getName(), "Manufacturer name should match");
        assertEquals(100.0, furnitureDto.getPrice().doubleValue(), 0.01, "Price should match");
        assertEquals(5, furnitureDto.getQuantity(), "Quantity should match");
    }

    @Test
    public void testMapListOfFurnitureToFurnitureDtos() {
        List<Furniture> furnitureList = Arrays.asList(
                new Furniture(3L, new Type(30L, "Sofa"), "Leather Sofa",
                        new Manufacturer(300L, "PQR Designs", "789 Oak St", "Germany"),
                        BigDecimal.valueOf(500.0), 2),
                new Furniture(4L, new Type(40L, "Bed"), "King-size Bed",
                        new Manufacturer(400L, "LMN Furnitures", "101 Pine St", "France"),
                        BigDecimal.valueOf(800.0), 1)
        );

        List<FurnitureDto> furnitureDtoList = furnitureMapper.map(furnitureList);

        assertEquals(2, furnitureDtoList.size(), "Size of mapped list should be 2");
        assertEquals("Leather Sofa", furnitureDtoList.get(0).getName(), "First name should match");
        assertEquals("King-size Bed", furnitureDtoList.get(1).getName(), "Second name should match");
    }

}
