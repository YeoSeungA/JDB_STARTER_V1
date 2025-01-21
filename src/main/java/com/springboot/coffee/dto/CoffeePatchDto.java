package com.springboot.coffee.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
public class CoffeePatchDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long coffeeId;

    @Column(nullable = false)
    private String korName;

    @Column(nullable = false)
    private String engName;

    @Column(nullable = false)
    private int price;
}
