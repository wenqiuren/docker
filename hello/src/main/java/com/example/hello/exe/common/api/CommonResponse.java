package com.example.hello.exe.common.api;

import lombok.Data;

@Data
public class CommonResponse<T> {
    private Boolean status;
    private String msg;
    private T data;

    public CommonResponse(Boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public CommonResponse(Boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    public static CommonResponse success(Object data){
        return new CommonResponse(Boolean.TRUE,data);
    }
    public static CommonResponse fail(String msg){
        return new CommonResponse(Boolean.FALSE,msg);
    }
}
