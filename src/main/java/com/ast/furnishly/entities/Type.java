package com.ast.furnishly.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type of Furniture
 *
 * Relation:
 * Many to One: Furniture -> Type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private Long id;
    private String name;
}
