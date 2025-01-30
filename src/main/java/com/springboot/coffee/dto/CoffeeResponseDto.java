package com.springboot.coffee.dto;

import com.springboot.coffee.entity.Coffee;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Builder는 꼭 써야한다. 없으면 그냥 null로 반환됨...
@Builder
@Getter
public class CoffeeResponseDto {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;
    private Coffee.CoffeeStatus coffeeStatus;

//    public String getCoffeeStatus() {
//        return coffeeStatus.getStatus();
//    }
}
