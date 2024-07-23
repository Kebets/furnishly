package com.ast.furnishly.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeDtoTest {

    @Test
    public void testIdGetter() {
        TypeDto typeDto = new TypeDto();
        typeDto.setId(42L);
        assertEquals(42L, typeDto.getId(), "Id should match the value set");
    }

    @Test
    public void testNameGetter() {
        TypeDto typeDto = new TypeDto();
        typeDto.setName("Example");
        assertEquals("Example", typeDto.getName(), "Name should match the value set");
    }

}
