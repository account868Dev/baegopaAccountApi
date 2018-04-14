package com.baegopa.account.commons.helper;

import com.baegopa.account.models.MessageCode;
import com.baegopa.account.models.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    private static final ResponseEntity<CommonResponse<Object>> SUCCESS = new ResponseEntity<>(new CommonResponse<>(MessageCode.SUCCESS), HttpStatus.OK);
    private static final ResponseEntity<CommonResponse<Object>> FAIL = new ResponseEntity<>(new CommonResponse<>(MessageCode.FAIL), HttpStatus.OK);

    public static ResponseEntity<CommonResponse<Object>> custom(MessageCode msgCode) {
        return new ResponseEntity<>(new CommonResponse<>(msgCode), HttpStatus.OK);
    }

    public static ResponseEntity<CommonResponse<Object>> custom(MessageCode msgCode, Object data) {
        return new ResponseEntity<>(new CommonResponse<>(msgCode, data), HttpStatus.OK);
    }

    public static ResponseEntity<CommonResponse<Object>> custom(MessageCode msgCode, Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(new CommonResponse<>(msgCode, data), httpStatus);
    }

    public static ResponseEntity<CommonResponse<Object>> success() {
        return SUCCESS;
    }

    public static ResponseEntity<CommonResponse<Object>> success(Object data) {
        return new ResponseEntity<>(new CommonResponse<>(MessageCode.SUCCESS, data), HttpStatus.OK);
    }

    public static ResponseEntity<CommonResponse<Object>> fail() {
        return FAIL;
    }

    public static ResponseEntity<CommonResponse<Object>> fail(Object data) {
        return new ResponseEntity<>(new CommonResponse<>(MessageCode.FAIL, data), HttpStatus.OK);
    }
}