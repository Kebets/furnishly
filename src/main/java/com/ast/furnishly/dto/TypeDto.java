package com.ast.furnishly.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TypeDto {
    private Long id;
    private String name;

    public TypeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
