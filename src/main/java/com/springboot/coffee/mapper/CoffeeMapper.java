package com.springboot.coffee.mapper;

import com.springboot.coffee.dto.CoffeePatchDto;
import com.springboot.coffee.dto.CoffeePostDto;
import com.springboot.coffee.dto.CoffeeResponseDto;
import com.springboot.coffee.entity.Coffee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
// PostDto를 Coffee Entity로 변환
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
//    PatchDto를 Coffee Entity로 변환
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
//    Coffee를 CoffeeResponseDto로
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
//    List<Coffee>를 List<CoffeeResponseDto>로  OrderCoffee가 List로 Coffee 정보를 갖고 있다.
    List<CoffeeResponseDto> coffeeToCoffeeResponseDtos(List<Coffee> coffees);
}
