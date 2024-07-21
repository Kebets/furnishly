package com.ast.furnishly.mappers;

import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.entities.Type;

import java.util.List;

public interface ManufacturerMapper {
    ManufacturerDto map(Manufacturer manufacturer);

    Manufacturer map(ManufacturerDto manufacturerDto);

    List<ManufacturerDto> map(List<Manufacturer> manufacturerList);
}
