package com.gree.service_feign.controller;

import com.gree.service_feign.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    private final ScheduleService scheduleService;

    @Autowired
    public SayHelloController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @RequestMapping("sayHello")
    public String sayHello(@RequestParam String name){
        return scheduleService.sayHiFromClientOne(name);
    }
}
