package com.ast.furnishly.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ManufacturerDto {
    private Long id;
    private String name;
    private String address;
    private String country;
}
