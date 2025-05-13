package com.study.testback.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRestaurantRequestDto {
    private String name;
    private String address;
    private String phoneNumber;
}
