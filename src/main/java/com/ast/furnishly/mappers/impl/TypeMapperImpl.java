package com.ast.furnishly.mappers.impl;

import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.entities.Type;
import com.ast.furnishly.mappers.TypeMapper;

import java.util.List;

public class TypeMapperImpl implements TypeMapper {
    @Override
    public TypeDto map(Type type) {
        return new TypeDto(type.getName());
    }

    @Override
    public List<TypeDto> map(List<Type> typeList) {
        return (List<TypeDto>) typeList.stream().map(Type::getName);
    }
}
