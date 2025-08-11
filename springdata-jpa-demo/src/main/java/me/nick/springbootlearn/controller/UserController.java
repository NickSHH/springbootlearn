package me.nick.springbootlearn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import me.nick.springbootlearn.service.UserService;

@RestController
public class UserController {
 
    @Resource
    private UserService userService;
 
    @GetMapping("/test")
    public String test() {
        return userService.test();
    }
}
