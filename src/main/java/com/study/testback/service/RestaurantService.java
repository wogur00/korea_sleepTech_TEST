package com.study.testback.service;

import com.study.testback.dto.request.PostRestaurantRequestDto;
import com.study.testback.dto.response.PostRestaurantResponseDto;
import com.study.testback.dto.response.RestaurantResponseDto;
import com.study.testback.entity.Restaurant;
import com.study.testback.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // Create
    public PostRestaurantResponseDto createRestaurant(PostRestaurantRequestDto dto) {
        Restaurant restaurant = Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        Restaurant saved = restaurantRepository.save(restaurant);

        return PostRestaurantResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .address(saved.getAddress())
                .phoneNumber(saved.getPhoneNumber())
                .build();
    }

    // Read 단건
    public RestaurantResponseDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .build();
    }

    // Read 전체 (페이징)
    public Page<RestaurantResponseDto> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable)
                .map(r -> RestaurantResponseDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .address(r.getAddress())
                        .phoneNumber(r.getPhoneNumber())
                        .build());
    }

    // Update
    public void updateRestaurant(Long id, PostRestaurantRequestDto dto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhoneNumber(dto.getPhoneNumber());

        restaurantRepository.save(restaurant);
    }

    // Delete
    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new EntityNotFoundException("Restaurant not found");
        }
        restaurantRepository.deleteById(id);
    }

    // 지역 필터
    public List<RestaurantResponseDto> getRestaurantsByRegion(String address) {
        return restaurantRepository.findAllByAddressContaining(address)
                .stream()
                .map(r -> RestaurantResponseDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .address(r.getAddress())
                        .phoneNumber(r.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }
}
