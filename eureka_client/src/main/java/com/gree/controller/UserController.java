package com.gree.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("/hi")
    public String sayHi(){
        LinkedHashMap map = new LinkedHashMap();
        return "Hello girl,I'm handsome";
    }
}
