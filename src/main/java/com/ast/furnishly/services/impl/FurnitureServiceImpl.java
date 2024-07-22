package com.ast.furnishly.services.impl;

import com.ast.furnishly.dto.FurnitureDto;
import com.ast.furnishly.entities.Furniture;
import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.mappers.FurnitureMapper;
import com.ast.furnishly.mappers.ManufacturerMapper;
import com.ast.furnishly.mappers.impl.FurnitureMapperImpl;
import com.ast.furnishly.mappers.impl.ManufacturerMapperImpl;
import com.ast.furnishly.repositories.FurnitureRepository;
import com.ast.furnishly.repositories.ManufacturerRepository;
import com.ast.furnishly.repositories.impl.FurnitureRepositoryImpl;
import com.ast.furnishly.repositories.impl.ManufacturerRepositoryImpl;
import com.ast.furnishly.services.FurnitureService;
import com.ast.furnishly.services.ManufacturerService;

import java.util.List;

/**
 * Implementation of the FurnitureService interface.
 */
public class FurnitureServiceImpl implements FurnitureService {
    private FurnitureRepository furnitureRepository = FurnitureRepositoryImpl.getInstance();
    private FurnitureMapper furnitureMapper = FurnitureMapperImpl.getInstance();
    private static FurnitureServiceImpl furnitureService;

    /**
     * Gets an instance of the FurnitureService.
     *
     * @return The FurnitureService instance.
     */
    public static synchronized FurnitureService getInstance() {
        if (furnitureService == null) {
            furnitureService = new FurnitureServiceImpl();
        }
        return furnitureService;
    }

    @Override
    public FurnitureDto findById(Long id) {
        Furniture furniture = furnitureRepository.findById(id).orElseThrow(() -> new NotFoundException("Furniture not found."));
        return furnitureMapper.map(furniture);
    }

    @Override
    public List<FurnitureDto> findAll() {
        List<Furniture> furnitureList = furnitureRepository.findAll();
        return furnitureMapper.map(furnitureList);
    }

    @Override
    public boolean delete(Long id) throws NotFoundException {
        checkFurnitureExists(id);
        return furnitureRepository.deleteById(id);
    }

    @Override
    public FurnitureDto save(FurnitureDto furnitureDto) {
        Furniture furniture = furnitureMapper.map(furnitureDto);
        furniture = furnitureRepository.save(furniture);
        return furnitureMapper.map(furniture);
    }

    @Override
    public void update(FurnitureDto furnitureDto) throws NotFoundException {
        checkFurnitureExists(furnitureDto.getId());
        Furniture furniture = furnitureMapper.map(furnitureDto);
        furnitureRepository.update(furniture);
    }

    private void checkFurnitureExists(Long Id) throws NotFoundException {
        if (!furnitureRepository.existsById(Id)) {
            throw new NotFoundException("Furniture not found.");
        }
    }
}
