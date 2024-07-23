package com.ast.furnishly.service.impl;
import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.mappers.ManufacturerMapper;
import com.ast.furnishly.repositories.ManufacturerRepository;
import com.ast.furnishly.services.impl.ManufacturerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ManufacturerServiceTest {
    @Mock
    private ManufacturerRepository manufacturerRepository;

    @Mock
    private ManufacturerMapper manufacturerMapper;

    @InjectMocks
    private ManufacturerServiceImpl manufacturerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        Long manufacturerId = 1L;
        Manufacturer manufacturer = new Manufacturer();
        when(manufacturerRepository.findById(manufacturerId)).thenReturn(java.util.Optional.of(manufacturer));

        ManufacturerDto expectedDto = new ManufacturerDto();
        when(manufacturerMapper.map(manufacturer)).thenReturn(expectedDto);

        ManufacturerDto actualDto = manufacturerService.findById(manufacturerId);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testFindAll() {
        Manufacturer manufacturer = new Manufacturer();
        List<Manufacturer> manufacturerList = Collections.singletonList(manufacturer);
        when(manufacturerRepository.findAll()).thenReturn(manufacturerList);

        ManufacturerDto expectedDto = new ManufacturerDto();
        when(manufacturerMapper.map(manufacturer)).thenReturn(expectedDto);

        List<ManufacturerDto> actualDtos = manufacturerService.findAll();

        assertEquals(1, actualDtos.size());
        assertEquals(expectedDto, actualDtos.get(0));
    }

    @Test
    void testDelete() throws NotFoundException {
        Long manufacturerId = 1L;
        when(manufacturerRepository.existsById(manufacturerId)).thenReturn(true);

        boolean result = manufacturerService.delete(manufacturerId);

        assertTrue(result);
        verify(manufacturerRepository).deleteById(manufacturerId);
    }

    @Test
    void testSave() {
        ManufacturerDto manufacturerDto = new ManufacturerDto();
        Manufacturer manufacturer = new Manufacturer();
        when(manufacturerMapper.map(manufacturerDto)).thenReturn(manufacturer);
        when(manufacturerRepository.save(manufacturer)).thenReturn(manufacturer);

        ManufacturerDto savedDto = manufacturerService.save(manufacturerDto);

        assertEquals(manufacturerDto, savedDto);
    }

    @Test
    void testUpdate() throws NotFoundException {
        Long manufacturerId = 1L;
        ManufacturerDto manufacturerDto = new ManufacturerDto();
        manufacturerDto.setId(manufacturerId);

        when(manufacturerRepository.existsById(manufacturerId)).thenReturn(true);

        manufacturerService.update(manufacturerDto);

        verify(manufacturerRepository).update(any(Manufacturer.class));
    }

}
