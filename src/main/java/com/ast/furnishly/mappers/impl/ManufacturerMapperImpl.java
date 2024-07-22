package com.ast.furnishly.mappers.impl;

import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.mappers.ManufacturerMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ManufacturerMapper} interface for converting between `Manufacturer`
 * domain objects and `ManufacturerDto` data transfer objects.
 */
public class ManufacturerMapperImpl implements ManufacturerMapper {
    private static ManufacturerMapper manufacturerMapper;

    /**
     * Returns an instance of the `ManufacturerMapperImpl`.
     *
     * @return The singleton instance of `ManufacturerMapperImpl`.
     */
    public static synchronized ManufacturerMapper getInstance(){
        if (manufacturerMapper == null){
            manufacturerMapper = new ManufacturerMapperImpl();
        }
        return manufacturerMapper;
    }

    @Override
    public ManufacturerDto map(Manufacturer manufacturer) {
        return new ManufacturerDto(manufacturer.getId(), manufacturer.getName(),
                manufacturer.getAddress(), manufacturer.getCountry());
    }

    @Override
    public Manufacturer map(ManufacturerDto manufacturerDto) {
        return new Manufacturer(manufacturerDto.getId(), manufacturerDto.getName(),
                manufacturerDto.getAddress(), manufacturerDto.getCountry());
    }

    @Override
    public List<ManufacturerDto> map(List<Manufacturer> manufacturerList) {
        return manufacturerList.stream().map(manufacturerMapper::map).collect(Collectors.toList());
    }
}
