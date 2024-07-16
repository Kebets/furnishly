package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.entities.Furniture;
import com.ast.furnishly.repositories.FurnitureRepository;

import java.util.List;
import java.util.Optional;

public class FurnitureRepositoryImpl implements FurnitureRepository {
    @Override
    public Furniture save(Furniture furniture) {
        return null;
    }

    @Override
    public void update(Furniture furniture) {

    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<Furniture> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Furniture> findAll() {
        return List.of();
    }

    @Override
    public boolean exitsById(Long id) {
        return false;
    }
}
