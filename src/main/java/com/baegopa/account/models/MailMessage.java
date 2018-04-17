package com.baegopa.account.models;

import lombok.Data;

@Data
public class MailMessage {
    private String from;
    private String to;
    private String message;
    private String subject;
    private String html;
}
