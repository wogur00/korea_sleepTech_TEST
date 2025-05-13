package com.study.testback.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
}
