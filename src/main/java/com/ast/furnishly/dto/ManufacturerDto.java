package com.ast.furnishly.dto;

import lombok.*;

/**
 * Data transfer object (DTO) for manufacturer information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufacturerDto {
    private Long id;
    private String name;
    private String address;
    private String country;
}
