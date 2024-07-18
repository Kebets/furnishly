package com.ast.furnishly.mappers;

import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.entities.Type;

import java.util.List;

public interface TypeMapper {
    TypeDto map(Type type);

    List<TypeDto> map(List<Type> typeList);
}