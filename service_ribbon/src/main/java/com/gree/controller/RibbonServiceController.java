package com.gree.controller;
;
import com.gree.redisService.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonServiceController {

    private final HelloService helloService;

    @Autowired
    public RibbonServiceController(HelloService helloService) {
        this.helloService = helloService;
    }


    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name){
        return helloService.sayHelloService(name);
    }
}
