package com.baegopa.account.models.response;

import com.baegopa.account.models.MessageCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = 7445895227461577053L;

    private Integer msgCode;
    private String msg;
    private T data;

    public CommonResponse() {
        this(null, null, null);
    }

    public CommonResponse(Integer msgCode, String msg, T data) {
        this.msgCode = msgCode;
        this.msg = msg;
        this.data = data;
    }

    public CommonResponse(MessageCode messageCode) {
        this(messageCode.getCode(), messageCode.name(), null);
    }

    public CommonResponse(MessageCode messageCode, T data) {
        this(messageCode.getCode(), messageCode.name(), data);
    }

    public Integer getMsgCode() {
        return msgCode;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}

