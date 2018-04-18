package com.baegopa.account.controllers;

import com.baegopa.account.commons.helper.ResponseHelper;
import com.baegopa.account.models.AuthType;
import com.baegopa.account.models.response.CommonResponse;
import com.baegopa.account.services.AuthService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private AuthService authService;

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse<Object>> authEmail(@RequestParam(value = "userId") Long userId
            , @RequestParam(value = "authKey") String authKey) {
        if(Strings.isNullOrEmpty(authKey))
            return ResponseHelper.fail();

        return ResponseHelper.success(authService.checkAuthKey(userId, authKey, AuthType.EMAIL_AUTH_NUMBER));
    }
}
