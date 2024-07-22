package com.ast.furnishly.mappers;

import com.ast.furnishly.dto.FurnitureDto;
import com.ast.furnishly.entities.Furniture;

import java.util.List;

/**
 * A mapper interface for converting between `Furniture` domain objects and `FurnitureDto` data transfer objects.
 * Implementations of this interface should provide methods to map between these two representations.
 * <p>
 * The purpose of this interface is to facilitate data transformation and abstraction between layers of an application.
 * It defines methods for mapping individual `Furniture` objects to `FurnitureDto` and vice versa, as well as handling
 * lists of such objects.
 * </p>
 * <p>
 * Example usage:
 * <pre>{@code
 * FurnitureMapper mapper = new MyFurnitureMapper(); // Your custom implementation
 * Furniture furniture = ...; // Get a `Furniture` object
 * FurnitureDto dto = mapper.map(furniture); // Convert to `FurnitureDto`
 * }</pre>
 * </p>
 * <p>
 * Note: Implementing classes should handle null input gracefully and return null or empty collections as appropriate.
 * </p>
 *
 * @see Furniture
 * @see FurnitureDto
 */
public interface FurnitureMapper {
    /**
     * Converts a `Furniture` object to a corresponding `FurnitureDto`.
     *
     * @param furniture The `Furniture` object to map.
     * @return The mapped `FurnitureDto`.
     */
    FurnitureDto map(Furniture furniture);

    /**
     * Converts a `FurnitureDto` object to a corresponding `Furniture`.
     *
     * @param furnitureDto The `FurnitureDto` object to map.
     * @return The mapped `Furniture`.
     */

    Furniture map(FurnitureDto furnitureDto);

    /**
     * Converts a list of `Furniture` objects to a list of corresponding `FurnitureDto` objects.
     *
     * @param furnitureList The list of `Furniture` objects to map.
     * @return The list of mapped `FurnitureDto` objects.
     */
    List<FurnitureDto> map(List<Furniture> furnitureList);
}
