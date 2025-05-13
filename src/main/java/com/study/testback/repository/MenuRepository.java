package com.study.testback.repository;

import com.study.testback.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByRestaurantId(Long restaurantId);

    List<Menu> findAllByPriceBetween(Double minPrice, Double maxPrice);
}
