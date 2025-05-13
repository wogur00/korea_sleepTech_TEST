package com.study.testback.repository;

import com.study.testback.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findAllByAddressContaining(String address);
}
