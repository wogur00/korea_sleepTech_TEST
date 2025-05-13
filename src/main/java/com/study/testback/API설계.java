package com.study.testback;

public class API설계 {
}
// 기능 이름 / HTTP 메서드 / URL 예시
// 식당 등록	/ POST / http://localhost:8080/api/restaurants
// 메뉴 등록	/ POST / http://localhost:8080/api/menus
// 전체 식당 조회 / GET / http://localhost:8080/api/restaurants?page=0&size=10
// 식당 단건 조회	/ GET	/ http://localhost:8080/api/restaurants/1
// 특정 식당 메뉴 조회	/ GET	/ http://localhost:8080/api/menus/restaurant/1
// 메뉴 단건 조회	/ GET	/ http://localhost:8080/api/menus/1
// 식당 정보  수정 / PUT	/ http://localhost:8080/api/restaurants/1
// 메뉴 정보 / 수정 / PUT	/ http://localhost:8080/api/menus/1
// 식당 삭제	/ DELETE / http://localhost:8080/api/restaurants/1
// 메뉴 삭제	/ DELETE / http://localhost:8080/api/menus/1
// 가격 범위 메뉴 조회	/ GET / http://localhost:8080/api/menus/filter?minPrice=10000&maxPrice=70000
// 지역별 식당 조회 / GET / http://localhost:8080/api/restaurants/region?address=부산