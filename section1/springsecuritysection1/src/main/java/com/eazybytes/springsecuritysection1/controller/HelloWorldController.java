package com.eazybytes.springsecuritysection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/welcome")
    public String hello() {
        return "Welcome to my Spring boot API";
    }
}
