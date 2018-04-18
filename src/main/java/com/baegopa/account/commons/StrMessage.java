package com.baegopa.account.commons;

public class StrMessage {
    public static class Account {
        public static final String userEmpty = "등록되지 않은 계정입니다.";
        public static final String passwordNotAuth = "패스워드가 틀립니다.";
        public static final String emailNotAuth = "이메일 인증을 해주세요";
    }

    public static class Email {
        public static final String emailAuthTitle = "이메일 인증번호는 메일 입니다.";
        public static final String emailAuthMessage = "이메일 인증번호는 %s 입니다.";
    }
}
