package com.study.testback.repository;

import com.study.testback.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 특정 레스토랑에 속한 메뉴 전체 조회 가능
    List<Menu> findAllByRestaurantId(Long restaurantId);

    // 특정 가격 범위의 메뉴 조회 가능
    List<Menu> findAllByPriceBetween(Double minPrice, Double maxPrice);
}
