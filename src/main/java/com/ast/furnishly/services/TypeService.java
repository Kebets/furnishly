package com.ast.furnishly.services;

import com.ast.furnishly.dto.TypeDto;

import java.util.List;

public interface TypeService {
    TypeDto findById(Long id);
    List<TypeDto> findAll();
}
