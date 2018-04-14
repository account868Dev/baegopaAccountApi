package com.baegopa.account;

import com.baegopa.account.reactive.AccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> accountRouterFunction(AccountHandler accountHandler) {
        return route(POST("/api/login").and(accept(MediaType.APPLICATION_JSON)), accountHandler::login);
//                .andRoute(GET("/api/customer/{id}").and(accept(MediaType.APPLICATION_JSON)), customerHandler::getCustomer)
//                .andRoute(POST("/api/customer/post").and(accept(MediaType.APPLICATION_JSON)), customerHandler::postCustomer)
//                .andRoute(PUT("/api/customer/put/{id}").and(accept(MediaType.APPLICATION_JSON)), customerHandler::putCustomer)
//                .andRoute(DELETE("/api/customer/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), customerHandler::deleteCustomer);
    }

}