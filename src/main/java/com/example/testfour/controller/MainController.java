package com.example.testfour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    @PermitAll
    public String home() {
        return "home";
    }
}
