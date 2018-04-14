package com.baegopa.account.repositories;

import com.baegopa.account.models.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailAndUseYn(@Param("email") String email, @Param("useYn") String useYn);
}
