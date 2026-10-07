package com.eazybytes.springsecuritysection4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {
    @GetMapping("/loans")
    public String hello() {
        return "Welcome to my Spring boot API";
    }
}
