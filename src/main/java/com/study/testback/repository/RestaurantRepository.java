package com.study.testback.repository;

import com.study.testback.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // 특정 지역(주소 포함)으로 검색 가능
    List<Restaurant> findAllByAddressContaining(String address);
}
