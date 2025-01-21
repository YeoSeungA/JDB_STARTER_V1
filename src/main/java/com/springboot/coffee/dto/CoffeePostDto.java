package com.springboot.coffee.dto;

import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
public class CoffeePostDto {
    @Column(nullable = false)
    private String korName;

    @Column(nullable = false)
    private String engName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, unique = true, length = 3)
    private String coffeeCode;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime modifedAt = LocalDateTime.now();
}
