package com.ast.furnishly.service.impl;
import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.entities.Type;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.mappers.TypeMapper;
import com.ast.furnishly.repositories.TypeRepository;
import com.ast.furnishly.services.impl.TypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TypeServiceImplTest {
    @Mock
    private TypeRepository typeRepository;

    @Mock
    private TypeMapper typeMapper;

    @InjectMocks
    private TypeServiceImpl typeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        Long typeId = 1L;
        Type type = new Type();
        when(typeRepository.findById(typeId)).thenReturn(java.util.Optional.of(type));

        TypeDto expectedDto = new TypeDto();
        when(typeMapper.map(type)).thenReturn(expectedDto);

        TypeDto actualDto = typeService.findById(typeId);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testFindAll() {
        Type type = new Type();
        List<Type> typeList = Collections.singletonList(type);
        when(typeRepository.findAll()).thenReturn(typeList);

        TypeDto expectedDto = new TypeDto();
        when(typeMapper.map(type)).thenReturn(expectedDto);

        List<TypeDto> actualDtos = typeService.findAll();

        assertEquals(1, actualDtos.size());
        assertEquals(expectedDto, actualDtos.get(0));
    }

    @Test
    void testDelete() throws NotFoundException {
        Long typeId = 1L;
        when(typeRepository.existsById(typeId)).thenReturn(true);

        boolean result = typeService.delete(typeId);

        assertTrue(result);
        verify(typeRepository).deleteById(typeId);
    }

    @Test
    void testSave() {
        TypeDto typeDto = new TypeDto();
        Type type = new Type();
        when(typeMapper.map(typeDto)).thenReturn(type);
        when(typeRepository.save(type)).thenReturn(type);

        TypeDto savedDto = typeService.save(typeDto);

        assertEquals(typeDto, savedDto);
    }

    @Test
    void testUpdate() throws NotFoundException {
        Long typeId = 1L;
        TypeDto typeDto = new TypeDto();
        typeDto.setId(typeId);

        when(typeRepository.existsById(typeId)).thenReturn(true);

        typeService.update(typeDto);

        verify(typeRepository).update(any(Type.class));
    }

}
