package com.ast.furnishly.services;

import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.exceptions.NotFoundException;

import java.util.List;

/**
 * Service interface for managing types.
 */
public interface TypeService {

    /**
     * Finds a type by its ID.
     *
     * @param id The ID of the type to find.
     * @return The corresponding TypeDto if found, or null if not found.
     */
    TypeDto findById(Long id);

    /**
     * Retrieves a list of all types.
     *
     * @return A list of TypeDto objects representing all available types.
     */
    List<TypeDto> findAll();

    /**
     * Deletes a type by its ID.
     *
     * @param id The ID of the type to delete.
     * @return true if the type was successfully deleted, false otherwise.
     * @throws NotFoundException If the type with the given ID is not found.
     */
    boolean delete(Long id) throws NotFoundException;

    /**
     * Saves a new type.
     *
     * @param typeDto The TypeDto object to save.
     * @return The saved TypeDto.
     */
    TypeDto save(TypeDto typeDto);

    /**
     * Updates an existing type.
     *
     * @param typeDto The updated TypeDto object.
     * @throws NotFoundException If the type with the given ID is not found.
     */
    void update(TypeDto typeDto) throws NotFoundException;
}
