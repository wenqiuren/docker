package com.example.hello.exe.common.model.api;

import lombok.Data;

@Data
public class CommonReq {
    private Integer pageIndex = 1;
    private Integer pageSize = 30;
    private String searchKey;
}
