package com.ast.furnishly.repositories;

import com.ast.furnishly.entities.Furniture;

/**
 * Generic repository interface for managing domain objects of type `Furniture`.
 *
 * @param <Furniture> The type of the domain object.
 * @param <Long> The type of the identifier (ID) for the domain object.
 */
public interface FurnitureRepository extends Repository<Furniture, Long> {
}
