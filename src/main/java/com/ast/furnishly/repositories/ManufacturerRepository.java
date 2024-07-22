package com.ast.furnishly.repositories;

import com.ast.furnishly.entities.Manufacturer;

/**
 * Generic repository interface for managing domain objects of type `Manufacturer`.
 *
 * @param <Manufacturer> The type of the domain object.
 * @param <Long> The type of the identifier (ID) for the domain object.
 */
public interface ManufacturerRepository extends Repository<Manufacturer, Long> {
}
