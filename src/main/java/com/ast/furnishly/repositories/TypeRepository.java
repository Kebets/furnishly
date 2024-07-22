package com.ast.furnishly.repositories;

import com.ast.furnishly.entities.Type;

/**
 * Generic repository interface for managing domain objects of type `Type`.
 *
 * @param <Type> The type of the domain object.
 * @param <Long> The type of the identifier (ID) for the domain object.
 */
public interface TypeRepository extends Repository<Type, Long>{
}
