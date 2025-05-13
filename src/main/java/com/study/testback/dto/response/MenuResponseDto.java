package com.study.testback.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuResponseDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
}
