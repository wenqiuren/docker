package com.example.hello.exe.hello.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDTO implements Serializable {
    private String name;
    private Integer age;
}
