package com.study.testback.controller;

import com.study.testback.dto.request.PostMenuRequestDto;
import com.study.testback.dto.response.MenuResponseDto;
import com.study.testback.dto.response.PostMenuResponseDto;
import com.study.testback.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<PostMenuResponseDto> createMenu(@RequestBody PostMenuRequestDto dto) {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuResponseDto>> getMenusByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(menuService.getMenusByRestaurantId(restaurantId));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MenuResponseDto>> getMenusByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice
    ) {
        return ResponseEntity.ok(menuService.getMenusByPriceRange(minPrice, maxPrice));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMenu(@PathVariable Long id, @RequestBody PostMenuRequestDto dto) {
        menuService.updateMenu(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
