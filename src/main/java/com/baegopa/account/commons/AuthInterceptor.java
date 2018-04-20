package com.baegopa.account.commons;

import com.baegopa.account.repositories.UserAuthKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends WebContentInterceptor {

    @Autowired
    private UserAuthKeyRepository userAuthKeyRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        //HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handler instanceof HandlerMethod) {
            AuthProcessing authProcessing = ((HandlerMethod) handler).getMethodAnnotation(AuthProcessing.class);

            if (authProcessing == null) {
                return super.preHandle(request, response, handler);
            } else {
                //TODO 추가작업
//                AuthValue value = new AuthValue();
//                value.setToken(request.getHeader("s-token"));
//                value.setId(request.getHeader("s-Id"));
//
//                if (1 > authRepository.authByToken(value)) return false;
//                else
                return super.preHandle(request, response, handler);
            }
        } else {
            return super.preHandle(request, response, handler);
        }
    }
}