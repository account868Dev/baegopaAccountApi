package com.baegopa.account.repositories;

import com.baegopa.account.models.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserReactiveRepository extends CrudRepository<User, Long> {
    Mono<User> findByEmailAndUseYn(@Param("email") String email, @Param("useYn") String useYn);
}
