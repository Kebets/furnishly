package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.entities.Type;
import com.ast.furnishly.repositories.TypeRepository;

import java.util.List;
import java.util.Optional;

public class TypeRepositoryImpl implements TypeRepository {

    @Override
    public Type save(Type type) {
        return null;
    }

    @Override
    public void update(Type type) {

    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<Type> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Type> findAll() {
        return List.of();
    }

    @Override
    public boolean exitsById(Long id) {
        return false;
    }
}
