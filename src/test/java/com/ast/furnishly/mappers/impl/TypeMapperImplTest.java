package com.ast.furnishly.mappers.impl;

import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.entities.Type;
import com.ast.furnishly.mappers.TypeMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeMapperImplTest {

    private final TypeMapper typeMapper = TypeMapperImpl.getInstance();

    @Test
    public void testMapTypeToTypeDto() {
        Type type = new Type(1L, "ExampleType");
        TypeDto typeDto = typeMapper.map(type);

        assertEquals(1L, typeDto.getId(), "Id should match");
        assertEquals("ExampleType", typeDto.getName(), "Name should match");
    }

    @Test
    public void testMapTypeDtoToType() {
        TypeDto typeDto = new TypeDto(2L, "AnotherType");
        Type type = typeMapper.map(typeDto);

        assertEquals(2L, type.getId(), "Id should match");
        assertEquals("AnotherType", type.getName(), "Name should match");
    }

    @Test
    public void testMapListOfTypesToTypeDtos() {
        List<Type> typeList = Arrays.asList(
                new Type(3L, "TypeA"),
                new Type(4L, "TypeB")
        );

        List<TypeDto> typeDtoList = typeMapper.map(typeList);

        assertEquals(2, typeDtoList.size(), "Size of mapped list should be 2");
        assertEquals("TypeA", typeDtoList.get(0).getName(), "First name should match");
        assertEquals("TypeB", typeDtoList.get(1).getName(), "Second name should match");
    }

}
