package com.baegopa.account.controllers;

import com.baegopa.account.commons.AuthProcessing;
import com.baegopa.account.commons.helper.ResponseHelper;
import com.baegopa.account.models.entity.User;
import com.baegopa.account.models.response.CommonResponse;
import com.baegopa.account.services.UserService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse<Object>> login(@RequestBody User user) throws Exception {
        if(user == null || user.getPassword().length < 1)
            return ResponseHelper.fail();

        return ResponseHelper.success(userService.login(user));
    }

    /*
    유저 정보 가져와서 토큰 갱신
    */
    @AuthProcessing
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse<Object>> findById(@RequestParam("id") Long id) throws Exception {
        if(id == null || id < 1L)
            return ResponseHelper.fail();

        return ResponseHelper.success(userService.findById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse<Object>> add(@RequestBody User user) throws Exception {
        if(user == null || Strings.isNullOrEmpty(user.getEmail()) || user.getPassword().length < 1)
            return ResponseHelper.fail();

        return ResponseHelper.success(userService.set(user));
    }

    @RequestMapping(value = "/check/email", method = RequestMethod.GET)
    public ResponseEntity<CommonResponse<Object>> add(@RequestParam(value = "email") String email) {
        if(Strings.isNullOrEmpty(email))
            return ResponseHelper.fail();

        return ResponseHelper.success(userService.checkEmail(email));
    }
}
