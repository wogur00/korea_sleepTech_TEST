package com.study.testback.service;

import com.study.testback.dto.request.PostMenuRequestDto;
import com.study.testback.dto.response.MenuResponseDto;
import com.study.testback.dto.response.PostMenuResponseDto;
import com.study.testback.dto.response.RestaurantResponseDto;
import com.study.testback.entity.Menu;
import com.study.testback.entity.Restaurant;
import com.study.testback.repository.MenuRepository;
import com.study.testback.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    // Create - 특정 Restaurant에 Menu 추가
    public PostMenuResponseDto createMenu(PostMenuRequestDto dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        Menu menu = Menu.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .restaurant(restaurant)
                .build();

        Menu saved = menuRepository.save(menu);

        return PostMenuResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .price(saved.getPrice())
                .description(saved.getDescription())
                .restaurant(RestaurantResponseDto.builder()
                        .id(restaurant.getId())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .phoneNumber(restaurant.getPhoneNumber())
                        .build())
                .build();
    }

    // Read - 특정 메뉴 단건 조회
    public MenuResponseDto getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        return MenuResponseDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .build();
    }

    // Read - 특정 Restaurant에 속한 전체 메뉴 조회
    public List<MenuResponseDto> getMenusByRestaurantId(Long restaurantId) {
        return menuRepository.findAllByRestaurantId(restaurantId)
                .stream()
                .map(menu -> MenuResponseDto.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .description(menu.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    // Update
    public void updateMenu(Long id, PostMenuRequestDto dto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        menu.setName(dto.getName());
        menu.setPrice(dto.getPrice());
        menu.setDescription(dto.getDescription());
        menu.setRestaurant(restaurant);

        menuRepository.save(menu);
    }

    // Delete
    public void deleteMenu(Long id) {
        if (!menuRepository.existsById(id)) {
            throw new EntityNotFoundException("Menu not found");
        }
        menuRepository.deleteById(id);
    }

    // 가격 필터링
    public List<MenuResponseDto> getMenusByPriceRange(Double min, Double max) {
        return menuRepository.findAllByPriceBetween(min, max)
                .stream()
                .map(menu -> MenuResponseDto.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .description(menu.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
