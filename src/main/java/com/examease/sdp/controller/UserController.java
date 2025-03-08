package com.examease.sdp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user") 
public class UserController {

    @GetMapping("/home")
    public String userHome() {
        return "Welcome to the User Home!";
    }
}
