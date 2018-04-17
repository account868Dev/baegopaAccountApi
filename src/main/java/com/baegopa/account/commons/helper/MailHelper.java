package com.baegopa.account.commons.helper;

import com.baegopa.account.models.MailMessage;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

/*
메일 보내는 펑션
 */
@Component
public class MailHelper {
    @Autowired private JavaMailSender javaMailSender;
    public final static String MAIL_SUCCESS = "인증 메일 성공";
    public final static String MAIL_FAIL = "인증 메일 실패";
    public final static String PASSWORD_MSG = "패스워드를 입력해주세요.(8자 ~ 16자 사이 영숫자 혼합)";

    @Async
    public void sendMail(MailMessage msg){
        SimpleMailMessage  mail = new SimpleMailMessage();
        mail.setFrom(Strings.isNullOrEmpty(msg.getFrom()) ? "peoplejobis@gmail.com" : msg.getFrom());
        mail.setTo(msg.getTo());
        mail.setSubject(msg.getSubject());
        mail.setText(msg.getMessage());
        javaMailSender.send(mail);
    }

    @Async
    public void sendHtmlMail(MailMessage msg) {
        javaMailSender.send((mimeMessage) -> {
            mimeMessage.setFrom(new InternetAddress(msg.getFrom()));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(msg.getTo()));
            mimeMessage.setSubject(msg.getSubject(), "utf-8");
            mimeMessage.setText(msg.getHtml(), "utf-8");
            mimeMessage.setHeader("content-Type", "text/html;charset=UTF-8");
        });
    }
}
