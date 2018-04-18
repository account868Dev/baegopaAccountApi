package com.baegopa.account.repositories;

import com.baegopa.account.models.entity.UserAuthKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAuthKeyRepository extends CrudRepository<UserAuthKey, Long> {
    Optional<UserAuthKey> findByUserIdAndType(@Param("userId") Long userId, @Param("type") String type);
}
