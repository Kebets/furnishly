package com.ast.furnishly.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The manufacturer of Furniture
 *
 * Relation:
 * Many to One: Furniture -> Manufacturer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {
    private Long id;
    private String name;
    private String country;
}
