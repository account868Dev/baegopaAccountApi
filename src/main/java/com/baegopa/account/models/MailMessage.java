package com.baegopa.account.models;

import lombok.Data;

@Data
public class MailMessage {
    private String from;
    private String to;
    private String message;
    private String subject;
    private String html;

    public MailMessage(){}
    public MailMessage(String from, String to, String message, String subject) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.subject = subject;
    }
    public MailMessage(String to, String message, String subject) {
        this.to = to;
        this.message = message;
        this.subject = subject;
    }
}
