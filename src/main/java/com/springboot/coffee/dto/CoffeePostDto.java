package com.springboot.coffee.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
public class CoffeePostDto {
    @NotBlank
    private String korName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+([A-Za-z]+)*$")
    private String engName;

    @NotBlank
    private int price;

    @NotBlank
    @Pattern(regexp = "^([A-Za-z]){3}$")
    private String coffeeCode;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifedAt = LocalDateTime.now();
}
