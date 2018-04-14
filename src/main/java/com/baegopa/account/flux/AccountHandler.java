package com.baegopa.account.flux;

import com.baegopa.account.services.AccountService;
import com.baegopa.account.models.entity.User;
import com.baegopa.account.models.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AccountHandler {

    @Autowired private AccountService accountService;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountService.reactiveLogin(userMono.block()), CommonResponse.class);
    }
}
