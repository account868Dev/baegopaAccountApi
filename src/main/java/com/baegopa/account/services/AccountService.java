package com.baegopa.account.services;

import com.baegopa.account.commons.helper.AuthCodeHelper;
import com.baegopa.account.models.MessageCode;
import com.baegopa.account.models.entity.User;
import com.baegopa.account.models.response.CommonResponse;
import com.baegopa.account.repositories.UserReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired private UserReactiveRepository userReactiveRepository;
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
