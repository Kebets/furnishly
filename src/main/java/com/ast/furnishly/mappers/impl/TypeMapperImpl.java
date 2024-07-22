package com.ast.furnishly.mappers.impl;

import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.entities.Type;
import com.ast.furnishly.mappers.TypeMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link TypeMapper} interface for converting between `Type` domain objects
 * and `TypeDto` data transfer objects.
 */
public class TypeMapperImpl implements TypeMapper {
    private static TypeMapper typeMapper;

    /**
     * Returns an instance of the `TypeMapperImpl`.
     *
     * @return The singleton instance of `TypeMapperImpl`.
     */
    public static synchronized TypeMapper getInstance(){
        if (typeMapper == null){
            typeMapper = new TypeMapperImpl();
        }
        return typeMapper;
    }

    @Override
    public TypeDto map(Type type) {
        return new TypeDto(type.getId(), type.getName());
    }

    @Override
    public Type map(TypeDto typeDto) {
        return new Type(typeDto.getId(), typeDto.getName());
    }

    @Override
    public List<TypeDto> map(List<Type> typeList) {
        return typeList.stream().map(typeMapper::map).collect(Collectors.toList());
    }
}
