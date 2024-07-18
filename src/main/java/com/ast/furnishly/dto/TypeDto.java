package com.ast.furnishly.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TypeDto {
    private String name;

    public TypeDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
