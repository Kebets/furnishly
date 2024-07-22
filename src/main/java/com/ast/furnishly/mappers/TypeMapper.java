package com.ast.furnishly.mappers;

import com.ast.furnishly.dto.TypeDto;
import com.ast.furnishly.entities.Type;

import java.util.List;

/**
 * A mapper interface for converting between `Type` domain objects and `TypeDto` data transfer objects.
 * Implementations of this interface should provide methods to map between these two representations.
 * <p>
 * The purpose of this interface is to facilitate data transformation and abstraction between layers of an application.
 * It defines methods for mapping individual `Type` objects to `TypeDto` and vice versa, as well as handling
 * lists of such objects.
 * </p>
 * <p>
 * Example usage:
 * <pre>{@code
 * TypeMapper mapper = new MyTypeMapper(); // Your custom implementation
 * Type type = ...; // Get a `Type` object
 * TypeDto dto = mapper.map(type); // Convert to `TypeDto`
 * }</pre>
 * </p>
 * <p>
 * Note: Implementing classes should handle null input gracefully and return null or empty collections as appropriate.
 * </p>
 *
 * @see Type
 * @see TypeDto
 */
public interface TypeMapper {

    /**
     * Converts a `Type` object to a corresponding `TypeDto`.
     *
     * @param type The `Type` object to map.
     * @return The mapped `TypeDto`.
     */
    TypeDto map(Type type);

    /**
     * Converts a `TypeDto` object to a corresponding `Type`.
     *
     * @param typeDto The `TypeDto` object to map.
     * @return The mapped `Type`.
     */
    Type map(TypeDto typeDto);

    /**
     * Converts a list of `Type` objects to a list of corresponding `TypeDto` objects.
     *
     * @param typeList The list of `Type` objects to map.
     * @return The list of mapped `TypeDto` objects.
     */
    List<TypeDto> map(List<Type> typeList);
}
