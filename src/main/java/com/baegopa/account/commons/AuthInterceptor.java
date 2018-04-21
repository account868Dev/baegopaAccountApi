package com.baegopa.account.commons;

import com.baegopa.account.models.AuthType;
import com.baegopa.account.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
여기는 인터셉터 인데
controller 접근 전에 공통적으로 처리를 해주는곳 이야
 */
@Component
public class AuthInterceptor extends WebContentInterceptor {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        if (handler instanceof HandlerMethod) {
            AuthProcessing authProcessing = ((HandlerMethod) handler).getMethodAnnotation(AuthProcessing.class);
            /*
            AuthProcessing 은 내가 만들어준 골뱅인데 이골뱅이가 붙어 있으면 아래와 로직을 타구
            안붙어 있으면 그냥 통과 하는 로직이야
             */
            if (authProcessing == null) {
                return super.preHandle(request, response, handler);
            } else {
                /*
                인증 처리
                 */
                Long id = Long.valueOf(request.getHeader("x-Id"));
                String token = request.getHeader("x-token");
                return  authService.checkAuthKey(id, token, AuthType.TOKEN)
                        ? super.preHandle(request, response, handler) : false;
            }
        } else {
            return super.preHandle(request, response, handler);
        }
    }
}