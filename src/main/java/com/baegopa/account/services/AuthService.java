package com.baegopa.account.services;

import com.baegopa.account.models.AuthType;
import com.baegopa.account.models.entity.UserAuthKey;
import com.baegopa.account.repositories.UserAuthKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired private UserAuthKeyRepository userAuthKeyRepository;

    /*
    @Async 붙이면
    자동으로 비동기 처리를 해죠
    대신 Return이 없는 것만 있을대는
    Future를 써야하는데 이건 콜백으루 가져와야하는...
     */
    @Async
    @Transactional
    public void setAuthKey(Long userId, String authKey, AuthType authType) {
        UserAuthKey userAuthKey = userAuthKeyRepository.findByUserIdAndType(userId, authType.getValue())
                .map( u -> {
                    u.setAuthKey(authKey);
                    u.setCreatedAt(LocalDateTime.now());
                    u.setUpdatedAt(LocalDateTime.now());
                    return u;
                }).orElse(new UserAuthKey(userId, authKey, authType.getValue()));

        userAuthKeyRepository.save(userAuthKey);
    }

    public boolean checkAuthKey(Long userId, String authKey, AuthType authType) {
        return userAuthKeyRepository.findByUserIdAndType(userId, authType.getValue())
                .filter(u -> authKey.equals(u.getAuthKey()))
                .orElse(null) != null;
    }
}

