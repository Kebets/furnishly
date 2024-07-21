package com.ast.furnishly.services.impl;

import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.exceptions.NotFoundException;
import com.ast.furnishly.mappers.ManufacturerMapper;
import com.ast.furnishly.mappers.impl.ManufacturerMapperImpl;
import com.ast.furnishly.repositories.ManufacturerRepository;
import com.ast.furnishly.repositories.impl.ManufacturerRepositoryImpl;
import com.ast.furnishly.services.ManufacturerService;

import java.util.List;

public class ManufacturerServiceImpl implements ManufacturerService {
    private ManufacturerRepository manufacturerRepository = ManufacturerRepositoryImpl.getInstance();
    private ManufacturerMapper manufacturerMapper = ManufacturerMapperImpl.getInstance();
    private static ManufacturerServiceImpl manufacturerService;

    public static synchronized ManufacturerService getInstance() {
        if (manufacturerService == null) {
            manufacturerService = new ManufacturerServiceImpl();
        }
        return manufacturerService;
    }

    @Override
    public ManufacturerDto findById(Long id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElseThrow(() -> new NotFoundException("Manufacturer not found."));
        return manufacturerMapper.map(manufacturer);
    }

    @Override
    public List<ManufacturerDto> findAll() {
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        return manufacturerMapper.map(manufacturerList);
    }

    @Override
    public boolean delete(Long id) throws NotFoundException {
        checkManufacturerExist(id);
        return manufacturerRepository.deleteById(id);
    }

    @Override
    public ManufacturerDto save(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = manufacturerMapper.map(manufacturerDto);
        manufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.map(manufacturer);
    }

    @Override
    public void update(Long id, ManufacturerDto manufacturerDto) throws NotFoundException {
        checkManufacturerExist(manufacturerDto.getId());
        Manufacturer manufacturer = manufacturerMapper.map(manufacturerDto);
        manufacturerRepository.update(manufacturer);
    }

    private void checkManufacturerExist(Long Id) throws NotFoundException {
        if (!manufacturerRepository.existsById(Id)) {
            throw new NotFoundException("Manufacturer not found.");
        }
    }
}
