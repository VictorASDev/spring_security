package com.eazybytes.springsecuritysection4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {
    @GetMapping("/notices")
    public String hello() {
        return "Welcome to my Spring boot API";
    }
}
