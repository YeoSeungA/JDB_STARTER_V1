package com.springboot.exception;

import lombok.Getter;

public enum ExcepionCode {
    MEMBER_NOT_FOUND(404,"Member not found"),
    MEMBER_EXIST(409, "Member exists"),
    COFFEE_NOT_FOUND(404,"Coffee not found"),
    COFFEE_CODE_EXIST(409, "Coffee Code exists"),
    ORDER_NOT_FOUND(404,"Oder not found"),
    CANNOT_CHANGE_ORDER(403, "Order can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status");

    @Getter
    private int numeber;

    @Getter
    private String description;

    ExcepionCode(int numeber, String description) {
        this.numeber = numeber;
        this.description = description;
    }
}
