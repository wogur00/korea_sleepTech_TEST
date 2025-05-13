package com.study.testback.controller;

import com.study.testback.dto.request.PostRestaurantRequestDto;
import com.study.testback.dto.response.PostRestaurantResponseDto;
import com.study.testback.dto.response.RestaurantResponseDto;
import com.study.testback.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<PostRestaurantResponseDto> createRestaurant(@RequestBody PostRestaurantRequestDto dto) {
        return ResponseEntity.ok(restaurantService.createRestaurant(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurant(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(restaurantService.getAllRestaurants(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable Long id, @RequestBody PostRestaurantRequestDto dto) {
        restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/region")
    public ResponseEntity<List<RestaurantResponseDto>> getByRegion(@RequestParam String address) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByRegion(address));
    }
}
