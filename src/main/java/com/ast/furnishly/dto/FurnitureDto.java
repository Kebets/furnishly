package com.ast.furnishly.dto;

import com.ast.furnishly.entities.Manufacturer;
import com.ast.furnishly.entities.Type;
import lombok.*;

import java.math.BigDecimal;

/**
 * Data transfer object (DTO) for furniture information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FurnitureDto {
    private Long id;
    private Type type;                // Type of furniture (e.g., chair, table, bed)
    private String name;
    private Manufacturer manufacturer;// Furniture manufacturer
    private BigDecimal price;
    private int quantity;             // Quantity available
}
