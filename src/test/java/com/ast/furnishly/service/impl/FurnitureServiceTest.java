package com.ast.furnishly.service.impl;
import com.ast.furnishly.dto.FurnitureDto;
import com.ast.furnishly.entities.Furniture;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.mappers.FurnitureMapper;
import com.ast.furnishly.repositories.FurnitureRepository;
import com.ast.furnishly.services.impl.FurnitureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class FurnitureServiceTest {
    @Mock
    private FurnitureRepository furnitureRepository;

    @Mock
    private FurnitureMapper furnitureMapper;

    @InjectMocks
    private FurnitureServiceImpl furnitureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        Long furnitureId = 1L;
        Furniture furniture = new Furniture();
        when(furnitureRepository.findById(furnitureId)).thenReturn(java.util.Optional.of(furniture));

        FurnitureDto expectedDto = new FurnitureDto();
        when(furnitureMapper.map(furniture)).thenReturn(expectedDto);

        FurnitureDto actualDto = furnitureService.findById(furnitureId);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testFindAll() {
        Furniture furniture = new Furniture();
        List<Furniture> furnitureList = Collections.singletonList(furniture);
        when(furnitureRepository.findAll()).thenReturn(furnitureList);

        FurnitureDto expectedDto = new FurnitureDto();
        when(furnitureMapper.map(furniture)).thenReturn(expectedDto);

        List<FurnitureDto> actualDtos = furnitureService.findAll();

        assertEquals(1, actualDtos.size());
        assertEquals(expectedDto, actualDtos.get(0));
    }

    @Test
    void testDelete() throws NotFoundException {
        Long furnitureId = 1L;
        when(furnitureRepository.existsById(furnitureId)).thenReturn(true);

        boolean result = furnitureService.delete(furnitureId);

        assertTrue(result);
        verify(furnitureRepository).deleteById(furnitureId);
    }

    @Test
    void testSave() {
        FurnitureDto furnitureDto = new FurnitureDto();
        Furniture furniture = new Furniture();
        when(furnitureMapper.map(furnitureDto)).thenReturn(furniture);
        when(furnitureRepository.save(furniture)).thenReturn(furniture);

        FurnitureDto savedDto = furnitureService.save(furnitureDto);

        assertEquals(furnitureDto, savedDto);
    }

    @Test
    void testUpdate() throws NotFoundException {
        Long furnitureId = 1L;
        FurnitureDto furnitureDto = new FurnitureDto();
        furnitureDto.setId(furnitureId);

        when(furnitureRepository.existsById(furnitureId)).thenReturn(true);

        furnitureService.update(furnitureDto);

        verify(furnitureRepository).update(any(Furniture.class));
    }

}
