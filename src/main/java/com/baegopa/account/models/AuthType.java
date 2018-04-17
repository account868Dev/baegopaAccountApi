package com.baegopa.account.models;

import lombok.Getter;

public enum AuthType {
    TOKEN("토큰", "TK"), EMAIL_AUTH_NUMBER("이메일 인증 번호", "EM");

    @Getter private final String label;
    @Getter private final String value;

    AuthType(String label, String value) {
        this.label = label;
        this.value = value;
    }
}
