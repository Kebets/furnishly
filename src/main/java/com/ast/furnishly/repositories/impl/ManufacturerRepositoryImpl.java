package com.ast.furnishly.repositories.impl;

import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.repositories.ManufacturerRepository;

import java.util.List;
import java.util.Optional;

public class ManufacturerRepositoryImpl implements ManufacturerRepository {
    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return null;
    }

    @Override
    public void update(Manufacturer manufacturer) {

    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Manufacturer> findAll() {
        return List.of();
    }

    @Override
    public boolean exitsById(Long id) {
        return false;
    }
}
