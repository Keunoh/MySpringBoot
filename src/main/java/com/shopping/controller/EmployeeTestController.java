package com.shopping.controller;

import com.shopping.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이 클래스는 컨트롤러(서블릿?)로 사용된다.
public class EmployeeTestController {

    @GetMapping(value = "/employee/")
    public Employee test(){
        Employee bean = new Employee();

        bean.setId("yusin");
        bean.setName("김유신");
        bean.setAge(20);

        return bean;
    }
}
