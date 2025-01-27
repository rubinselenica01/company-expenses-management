package com.company.expenses_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
public class HelloController {

    @GetMapping()
    public String hello(){
        return "Hello from authorized!";
    }
}
