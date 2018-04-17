package com.baegopa.account.models;

import lombok.Getter;

public enum MessageCode {
    SUCCESS(1), FAIL(-1),
    ALREADY_FRIENDS(101), INVALID_USER(-101), NOT_PLUS_FRIEND(-102), INVALID_PARAMS(-109), OVERLAP(-110),
    NOT_LOGIN(-510), MAX_PLUS_FRIENDS_LIMIT(-523), IS_PC(-600);

    @Getter private final Integer code;

    MessageCode(Integer code) {
        this.code = code;
    }
}