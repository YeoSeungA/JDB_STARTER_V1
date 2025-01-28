package com.springboot.coffee.controller;

import com.springboot.coffee.dto.CoffeePatchDto;
import com.springboot.coffee.dto.CoffeePostDto;
import com.springboot.coffee.dto.CoffeeResponseDto;
import com.springboot.coffee.entity.Coffee;
import com.springboot.coffee.mapper.CoffeeMapper;
import com.springboot.coffee.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v11/coffees")
@Valid
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper coffeeMapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper coffeeMapper) {
        this.coffeeService = coffeeService;
        this.coffeeMapper = coffeeMapper;
    }

    @PostMapping
    public ResponseEntity postCoffee(@RequestBody @Valid CoffeePostDto coffeePostDto) {
////        Dto를 Coffee 형태로 바꾸고 받아야 한다.
//        Coffee postCoffee = coffeeMapper.coffeePostDtoToCoffee(coffeePostDto);
////        Service 계층으로 보내야한다.
//        coffeeService.createdCoffee(postCoffee);
//        위 두 줄을 한줄로 합치다.
        Coffee coffee = coffeeService.createdCoffee(coffeeMapper.coffeePostDtoToCoffee(coffeePostDto));
//        받아온 결과를 Entity 에서 Dto 형태로 바꿔야 한다.
        CoffeeResponseDto response = coffeeMapper.coffeeToCoffeeResponseDto(coffee);
//        ResponseEntity로 반환
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@Positive @PathVariable("coffee-id") long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
//      해당 coffeeId를 PatchDto에 설정해 데이터가 일관되도록 만든다.
        coffeePatchDto.setCoffeeId(coffeeId);
//        Dto를 Entity 형태로 변환 후 Service에서 Patch가 실행 되게 했다.
        Coffee coffee = coffeeService.updatedCoffee(coffeeMapper.coffeePatchDtoToCoffee(coffeePatchDto));
        CoffeeResponseDto response = coffeeMapper.coffeeToCoffeeResponseDto(coffee);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@Positive @PathVariable long coffeeId) {
        Coffee coffee = coffeeService.findCoffee(coffeeId);
        CoffeeResponseDto response = coffeeMapper.coffeeToCoffeeResponseDto(coffee);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        List<Coffee> coffees = coffeeService.findCoffees();
        List<CoffeeResponseDto> responseDtos = coffeeMapper.coffeeToCoffeeResponseDtos(coffees);

        return new ResponseEntity(responseDtos,HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@Positive @PathVariable long coffeeId) {
        coffeeService.deleteCoffee(coffeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
