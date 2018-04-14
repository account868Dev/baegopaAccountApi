package com.baegopa.account.services;

import com.baegopa.account.commons.AuthCodeHelper;
import com.baegopa.account.models.MessageCode;
import com.baegopa.account.models.entity.User;
import com.baegopa.account.models.response.CommonResponse;
import com.baegopa.account.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.baegopa.account.repositories.UserReactiveRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired private UserReactiveRepository userReactiveRepository;
    @Autowired private UserRepository userRepository;

    public Mono<CommonResponse> reactiveLogin(User user) {
        return userReactiveRepository.findByEmailAndUseYn(user.getEmail(), "Y")
                .map( u -> {
                    try {
                        user.setPassword(AuthCodeHelper.getEncSHA256(new String(user.getPassword())).toCharArray());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(user.getPassword().equals(u.getPassword())){
                        return new CommonResponse<>(MessageCode.SUCCESS, u);
                    }
                    return new CommonResponse<>(MessageCode.FAIL);
                });
    }

    @Transactional
    public User set(User user) throws Exception {
        user.setPassword(AuthCodeHelper.getEncSHA256(new String(user.getPassword())).toCharArray());

        final User account = userRepository.findByEmailAndUseYn(user.getEmail(), "Y")
                .map( u -> {
                    u.setName(user.getName());
                    u.setPassword(user.getPassword());
                    u.setUpdatedAt(LocalDateTime.now());
                    return u;
                }).orElse(user);
        return userRepository.save(account);
    }

    public CommonResponse login(User user) throws Exception {
        user.setPassword(AuthCodeHelper.getEncSHA256(new String(user.getPassword())).toCharArray());

        return userRepository.findByEmailAndUseYn(user.getEmail(), "Y")
                .map( u -> {
                    if(user.getPassword().equals(u.getPassword())){
                        return new CommonResponse<>(MessageCode.SUCCESS, u);
                    }
                    return new CommonResponse<>(MessageCode.FAIL);
                })
                .orElse(new CommonResponse<>(MessageCode.FAIL));
    }
}
