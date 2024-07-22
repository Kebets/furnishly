package com.ast.furnishly.services;

import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.exceptions.NotFoundException;

import java.util.List;

/**
 * Service interface for managing manufacturers.
 */
public interface ManufacturerService {

    /**
     * Finds a manufacturer by its ID.
     *
     * @param id The ID of the manufacturer to find.
     * @return The corresponding ManufacturerDto if found, or null if not found.
     */
    ManufacturerDto findById(Long id);

    /**
     * Retrieves a list of all manufacturers.
     *
     * @return A list of ManufacturerDto objects representing all available manufacturers.
     */
    List<ManufacturerDto> findAll();

    /**
     * Deletes a manufacturer by its ID.
     *
     * @param id The ID of the manufacturer to delete.
     * @return true if the manufacturer was successfully deleted, false otherwise.
     * @throws NotFoundException If the manufacturer with the given ID is not found.
     */
    boolean delete(Long id) throws NotFoundException;

    /**
     * Saves a new manufacturer.
     *
     * @param manufacturerDto The ManufacturerDto object to save.
     * @return The saved ManufacturerDto.
     */
    ManufacturerDto save(ManufacturerDto manufacturerDto);

    /**
     * Updates an existing manufacturer.
     *
     * @param manufacturerDto The updated ManufacturerDto object.
     * @throws NotFoundException If the manufacturer with the given ID is not found.
     */
    void update(ManufacturerDto manufacturerDto) throws NotFoundException;
}
