package com.baegopa.account.models.entity;

import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

/*
@Table을 해서 DB 테이블 명이랑 매핑을 하면대
@Column은 DB 컬럼 이랑 연결 하는거구
DB컬럼 명이랑 변수명이 다르면 저렇게 괄호 name = "DB컬럼명" 해주면대
@Id 랑 및에 있는건 PrimaryKey 선언
@Data 는 롬복인데 자동으로 get/set toString equals를 생성해줘
 */
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

//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "id", referencedColumnName = "user_id")
//    private List<UserAuthKey> userAuthKeyList = Lists.newArrayList();

    public User(){}

    public User(String email, String name, char[] password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.snsType = "N";
        this.useYn = "Y";
        this.authYn = "N";
        this.createdAt = LocalDateTime.now();
    }

    public User(String email, String name, char[] password, String snsType, String snsId) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.snsType = snsType;
        this.snsId = snsId;
        this.useYn = "Y";
        this.authYn = "N";
        this.createdAt = LocalDateTime.now();
    }
}
