package com.ast.furnishly.mappers;

import com.ast.furnishly.dto.ManufacturerDto;
import com.ast.furnishly.entities.Manufacturer;

import java.util.List;

/**
 * A mapper interface for converting between `Manufacturer` domain objects and `ManufacturerDto` data transfer objects.
 * Implementations of this interface should provide methods to map between these two representations.
 * <p>
 * The purpose of this interface is to facilitate data transformation and abstraction between layers of an application.
 * It defines methods for mapping individual `Manufacturer` objects to `ManufacturerDto` and vice versa, as well as handling
 * lists of such objects.
 * </p>
 * <p>
 * Example usage:
 * <pre>{@code
 * ManufacturerMapper mapper = new MyManufacturerMapper(); // Your custom implementation
 * Manufacturer manufacturer = ...; // Get a `Manufacturer` object
 * ManufacturerDto dto = mapper.map(manufacturer); // Convert to `ManufacturerDto`
 * }</pre>
 * </p>
 * <p>
 * Note: Implementing classes should handle null input gracefully and return null or empty collections as appropriate.
 * </p>
 *
 * @see Manufacturer
 * @see ManufacturerDto
 */
public interface ManufacturerMapper {

    /**
     * Converts a `Manufacturer` object to a corresponding `ManufacturerDto`.
     *
     * @param manufacturer The `Manufacturer` object to map.
     * @return The mapped `ManufacturerDto`.
     */
    ManufacturerDto map(Manufacturer manufacturer);

    /**
     * Converts a `ManufacturerDto` object to a corresponding `Manufacturer`.
     *
     * @param manufacturerDto The `ManufacturerDto` object to map.
     * @return The mapped `Manufacturer`.
     */
    Manufacturer map(ManufacturerDto manufacturerDto);

    /**
     * Converts a list of `Manufacturer` objects to a list of corresponding `ManufacturerDto` objects.
     *
     * @param manufacturerList The list of `Manufacturer` objects to map.
     * @return The list of mapped `ManufacturerDto` objects.
     */
    List<ManufacturerDto> map(List<Manufacturer> manufacturerList);
}
