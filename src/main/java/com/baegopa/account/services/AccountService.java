package com.baegopa.account.services;

import com.baegopa.account.commons.helper.AuthCodeHelper;
import com.baegopa.account.models.MessageCode;
import com.baegopa.account.models.entity.User;
import com.baegopa.account.models.response.CommonResponse;
import com.baegopa.account.repositories.UserReactiveRepository;
import com.baegopa.account.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class AccountService {
    @Autowired private UserReactiveRepository userReactiveRepository;
    @Autowired private UserRepository userRepository;

    /*
    스프링 편한것중에 하나가
    @Transactional을 사용하면 자동으로 트랜잭션 처리를 해죠
    너 루비에서 한것처럼 저렇게 빼와서 있으면 하나씩 업데이트
    없으면 save
     */
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

    /*
    여기서 부터 스프링 5 reactive
     */

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
}
