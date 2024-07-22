package com.ast.furnishly.services;

import com.ast.furnishly.dto.FurnitureDto;
import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.exceptions.NotFoundException;

import java.util.List;

/**
 * Service interface for managing furniture items.
 */
public interface FurnitureService {

    /**
     * Finds a furniture item by its ID.
     *
     * @param id The ID of the furniture item to find.
     * @return The corresponding FurnitureDto if found, or null if not found.
     */
    FurnitureDto findById(Long id);

    /**
     * Retrieves a list of all furniture items.
     *
     * @return A list of FurnitureDto objects representing all available furniture items.
     */
    List<FurnitureDto> findAll();

    /**
     * Deletes a furniture item by its ID.
     *
     * @param id The ID of the furniture item to delete.
     * @return true if the furniture item was successfully deleted, false otherwise.
     * @throws NotFoundException If the furniture item with the given ID is not found.
     */
    boolean delete(Long id) throws NotFoundException;

    /**
     * Saves a new furniture item.
     *
     * @param furnitureDto The FurnitureDto object to save.
     * @return The saved FurnitureDto.
     */
    FurnitureDto save(FurnitureDto furnitureDto);

    /**
     * Updates an existing furniture item.
     *
     * @param furnitureDto The updated FurnitureDto object.
     * @throws NotFoundException If the furniture item with the given ID is not found.
     */
    void update(FurnitureDto furnitureDto) throws NotFoundException;
}
