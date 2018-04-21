package com.baegopa.account.models.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_choice_logs")
public class UserChoiceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "food_id")
    private Long foodId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
