package com.ast.furnishly.dto;

import lombok.*;

/**
 * Data transfer object (DTO) for type information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeDto {
    private Long id;
    private String name;
}
