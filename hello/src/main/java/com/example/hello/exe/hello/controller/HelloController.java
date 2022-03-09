package com.example.hello.exe.hello.controller;

import com.example.hello.exe.common.model.api.CommonReq;
import com.example.hello.exe.common.model.api.CommonResponse;
import com.example.hello.exe.hello.domain.dto.PersonDTO;
import com.example.hello.exe.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController  {
    @Autowired
    HelloService helloService;
    @RequestMapping("/test")
    public CommonResponse<List<PersonDTO>> hello(@RequestBody CommonReq commonReq){
        return helloService.findByCondition(commonReq);
    }

    @RequestMapping("/testMq")
    public CommonResponse<Boolean> testMq(@RequestBody CommonReq commonReq){
        return helloService.testMq(commonReq);
    }
}
