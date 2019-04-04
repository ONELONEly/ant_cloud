package com.gree.eureka_client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope //保证refresh之后能够拿到最新的值，不添加的话可能会造成调用缓存的结果
public class ChatServerController {
    private final Logger logger = LoggerFactory.getLogger(FallbackProvider.class);

    @Value("${server.port}")
    String port;

    @Value("${content}")
    String content;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name){
        logger.info("rose");
        return "Hello!"+name+",I'm from port"+port+",content:"+content;
    }

    @RequestMapping("/hi")
    public String sayHi(){
        return "i'm jinyu mura~";
    }

    @RequestMapping("/info")
    public String info(){
        return restTemplate.getForObject("http://localhost:8762/info",String.class);
    }
}
