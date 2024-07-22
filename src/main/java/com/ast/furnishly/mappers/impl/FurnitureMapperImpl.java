package com.ast.furnishly.mappers.impl;

import com.ast.furnishly.dto.FurnitureDto;
import com.ast.furnishly.entities.Furniture;
import com.ast.furnishly.mappers.FurnitureMapper;
import com.ast.furnishly.mappers.ManufacturerMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link FurnitureMapper} interface for converting between `Furniture`
 * domain objects and `FurnitureDto` data transfer objects.
 */
public class FurnitureMapperImpl implements FurnitureMapper {
    private static FurnitureMapper furnitureMapper;

    /**
     * Returns an instance of the `FurnitureMapperImpl`.
     *
     * @return The singleton instance of `FurnitureMapperImpl`.
     */
    public static synchronized FurnitureMapper getInstance(){
        if (furnitureMapper == null){
            furnitureMapper = new FurnitureMapperImpl();
        }
        return furnitureMapper;
    }

    @Override
    public FurnitureDto map(Furniture furniture) {
        return new FurnitureDto(furniture.getId(), furniture.getType(),
                furniture.getName(), furniture.getManufacturer(),
                furniture.getPrice(), furniture.getQuantity());
    }

    @Override
    public Furniture map(FurnitureDto furnitureDto) {
        return new Furniture(furnitureDto.getId(), furnitureDto.getType(),
                furnitureDto.getName(), furnitureDto.getManufacturer(),
                furnitureDto.getPrice(), furnitureDto.getQuantity());
    }

    @Override
    public List<FurnitureDto> map(List<Furniture> furnitureList) {
        return furnitureList.stream().map(furnitureMapper::map).collect(Collectors.toList());
    }
}
