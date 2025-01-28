package com.springboot.coffee.dto;

import com.springboot.coffee.entity.Coffee;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class CoffeePatchDto {
    private long coffeeId;

    @NotBlank(message = "커피명(한글)은 공백이 아니어야 합니다.")
    private String korName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+([A-Za-z]+)*$")
    private String engName;

    @Range(min = 100, max = 50000)
    private int price;

    private Coffee.CoffeeStatus coffeeStatus;
//    Controller에서 patchDto에 해당 Id로 데이터 일관을 위해 함수를 만들자.

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }
}
