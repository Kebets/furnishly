package com.ast.furnishly.mappers.impl;

import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.mappers.ManufacturerMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ManufacturerMapperImplTest {

    private final ManufacturerMapper manufacturerMapper = ManufacturerMapperImpl.getInstance();

    @Test
    public void testMapManufacturerToManufacturerDto() {
        Manufacturer manufacturer = new Manufacturer(1L, "ExampleManufacturer", "123 Main St", "USA");
        ManufacturerDto manufacturerDto = manufacturerMapper.map(manufacturer);

        assertEquals(1L, manufacturerDto.getId(), "Id should match");
        assertEquals("ExampleManufacturer", manufacturerDto.getName(), "Name should match");
        assertEquals("123 Main St", manufacturerDto.getAddress(), "Address should match");
        assertEquals("USA", manufacturerDto.getCountry(), "Country should match");
    }

    @Test
    public void testMapManufacturerDtoToManufacturer() {
        ManufacturerDto manufacturerDto = new ManufacturerDto(2L, "AnotherManufacturer", "456 Elm St", "Canada");
        Manufacturer manufacturer = manufacturerMapper.map(manufacturerDto);

        assertEquals(2L, manufacturer.getId(), "Id should match");
        assertEquals("AnotherManufacturer", manufacturer.getName(), "Name should match");
        assertEquals("456 Elm St", manufacturer.getAddress(), "Address should match");
        assertEquals("Canada", manufacturer.getCountry(), "Country should match");
    }

    @Test
    public void testMapListOfManufacturersToManufacturerDtos() {
        List<Manufacturer> manufacturerList = Arrays.asList(
                new Manufacturer(3L, "ManufacturerA", "789 Oak St", "Germany"),
                new Manufacturer(4L, "ManufacturerB", "101 Pine St", "France")
        );

        List<ManufacturerDto> manufacturerDtoList = manufacturerMapper.map(manufacturerList);

        assertEquals(2, manufacturerDtoList.size(), "Size of mapped list should be 2");
        assertEquals("ManufacturerA", manufacturerDtoList.get(0).getName(), "First name should match");
        assertEquals("ManufacturerB", manufacturerDtoList.get(1).getName(), "Second name should match");
    }

}
