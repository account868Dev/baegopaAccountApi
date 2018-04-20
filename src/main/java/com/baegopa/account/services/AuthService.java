package com.baegopa.account.services;

import com.baegopa.account.models.AuthType;
import com.baegopa.account.models.MessageCode;
import com.baegopa.account.models.entity.UserAuthKey;
import com.baegopa.account.models.response.CommonResponse;
import com.baegopa.account.repositories.UserAuthKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired private UserAuthKeyRepository userAuthKeyRepository;

    @Async
    @Transactional
    public void setAuthKey(Long userId, String authKey, AuthType authType) {
        UserAuthKey userAuthKey = userAuthKeyRepository.findByUserIdAndType(userId, authType.getValue())
                .map( u -> {
                    u.setAuthKey(authKey);
                    u.setCreatedAt(LocalDateTime.now());
                    return u;
                }).orElse(new UserAuthKey(userId, authKey, authType.getValue()));

        userAuthKeyRepository.save(userAuthKey);
    }

    public CommonResponse checkAuthKey(Long userId, String authKey, AuthType authType) {
        return userAuthKeyRepository.findByUserIdAndType(userId, authType.getValue())
                .filter( u -> authKey.equals(u.getAuthKey()))
                .orElse(null) == null
                ? new CommonResponse<>(MessageCode.FAIL) : new CommonResponse<>(MessageCode.SUCCESS);
    }
}

