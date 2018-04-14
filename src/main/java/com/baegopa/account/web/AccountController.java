package com.baegopa.account.web;

import com.baegopa.account.commons.ResponseHelper;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

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
}
