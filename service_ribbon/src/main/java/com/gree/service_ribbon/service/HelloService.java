package com.gree.service_ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    private final RestTemplate restTemplate;

    @Autowired
    public HelloService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHelloService(String name){
        return restTemplate.getForObject("http://CLIENT-SAY-HI/sayHello?name="+name,String.class);
    }

    public String hiError(String name){
        return "Hi!"+name+",Error!";
    }
}
