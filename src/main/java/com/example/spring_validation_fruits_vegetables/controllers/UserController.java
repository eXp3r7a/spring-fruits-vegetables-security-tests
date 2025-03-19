package com.example.spring_validation_fruits_vegetables.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/login")
    public String login(){
        return "users/login";
    }
    @GetMapping("/profile")
    public String userInformation(){

        return "users/user_info";
    }
}
