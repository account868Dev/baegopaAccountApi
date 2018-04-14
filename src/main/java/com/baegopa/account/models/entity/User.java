package com.baegopa.account.models.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private char[] password;

    @Column(name = "sns_type")
    private String snsType;

    @Column(name = "sns_id")
    private String snsId;

    @Column(name = "use_yn")
    private String useYn;

    @Column(name = "auth_yn")
    private String authYn;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Transient
    private String token;

    public User(){}

    public User(String email, String name, char[] password, String snsType, String snsId) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.snsType = snsType;
        this.snsId = snsId;
        this.createdAt = LocalDateTime.now();
    }
}
