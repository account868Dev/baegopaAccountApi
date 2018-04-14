package com.baegopa.account.repositories;

import com.baegopa.account.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
/*
여기가 DB 접근하는 곳인데
메서드명에 따라 DB 쿼리를 자동으로 처리해주고
findBy컬럼명
쿼리를 작성하고 싶으면
아래 처럼 해주면 대
이해 잘안될서야 처음에는 설명이 부족하면 이야기하고!
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailAndUseYn(@Param("email") String email, @Param("useYn") String useYn);

    @Query("SELECT u FROM User u WHERE email = :email AND useYn = :useYn")
    Optional<User> getByEmailAndUseYn(@Param("email") String email, @Param("useYn") String useYn);
}
