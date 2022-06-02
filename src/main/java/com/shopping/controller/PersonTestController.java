package com.shopping.controller;

import com.shopping.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonTestController {
    @GetMapping(value = "/person/")
    public Person test(){
        Person bean = new Person();

        bean.setId("katlz");
        bean.setName("keunoh");
        bean.setAddress("my home");
        bean.setSalary(1000);

        return bean;
    }
}
