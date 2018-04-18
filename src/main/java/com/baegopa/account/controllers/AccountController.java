package com.baegopa.account.controllers;

import com.baegopa.account.commons.helper.ResponseHelper;
import com.baegopa.account.models.MessageCode;
import com.baegopa.account.models.entity.User;
import com.baegopa.account.models.response.CommonResponse;
import com.baegopa.account.services.AccountService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
RestController 를 사용하면
객체를 ResponseEntity 감싸서 리턴 했을때 알아서 Json으로 만들어줘

Model에 entity가 데이터 베이스랑 연결된 모델이야
realm 하듯이 하면 대구
value는 requestParam을 안쓰고 객체로 받을 인풋 모델
response는 리턴 시킬 모델

이해 잘안될거야 처음에는 설명이 부족하면 이야기하고!
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse<Object>> login(@RequestBody User user) throws Exception {
        if(user == null || Strings.isNullOrEmpty(user.getEmail()) || user.getPassword().length < 1)
            return ResponseHelper.fail(MessageCode.INVALID_USER);

        return ResponseHelper.success(accountService.login(user));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse<Object>> add(@RequestBody User user) throws Exception {
        if(user == null || Strings.isNullOrEmpty(user.getEmail()) || user.getPassword().length < 1)
            return ResponseHelper.fail(MessageCode.INVALID_USER);

        return ResponseHelper.success(accountService.set(user));
    }

    @RequestMapping(value = "/check/email", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse<Object>> add(@RequestParam(value = "email") String email) {
        if(Strings.isNullOrEmpty(email))
            return ResponseHelper.fail();

        return ResponseHelper.success(accountService.checkEmail(email));
    }
}
