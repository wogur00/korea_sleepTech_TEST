package com.study.testback.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostMenuRequestDto {
    private String name;
    private Double price;
    private String description;
    private Long restaurantId;
}
