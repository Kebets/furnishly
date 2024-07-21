package com.ast.furnishly.services;

import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.exceptions.NotFoundException;

import java.util.List;

public interface TypeService {
    TypeDto findById(Long id);
    List<TypeDto> findAll();
    boolean delete(Long id) throws NotFoundException;
    TypeDto save(TypeDto typeDto);
    void update(Long id, TypeDto typeDto) throws NotFoundException;
}
