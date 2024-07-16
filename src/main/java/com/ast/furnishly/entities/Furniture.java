package com.ast.furnishly.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Furniture entity
 *
 * Relation:
 * Many To One: Furniture -> Type
 * Many To One: Furniture -> Manufacturer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Furniture {
    private Long id;
    private Type type;
    private String name;
    private Manufacturer manufacturer;
    private BigDecimal price;
    private int quantity;
}
