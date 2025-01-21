package com.springboot.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException {
    @Getter
    private ExcepionCode excepionCode;

    public BusinessLogicException(ExcepionCode excepionCode) {
        super(excepionCode.getDescription());
        this.excepionCode = excepionCode;
    }
}
