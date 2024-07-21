package com.ast.furnishly.services;

import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.exceptions.NotFoundException;

import java.util.List;

public interface ManufacturerService {
    ManufacturerDto findById(Long id);
    List<ManufacturerDto> findAll();
    boolean delete(Long id) throws NotFoundException;
    ManufacturerDto save(ManufacturerDto manufacturerDto);
    void update(Long id, ManufacturerDto manufacturerDto) throws NotFoundException;
}
