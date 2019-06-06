package com.gree.serviceApi.hystric;

import com.gree.serviceApi.ScheduleService;
import org.springframework.stereotype.Component;

@Component
public class ScheduleServiceHystric implements ScheduleService {

    @Override
    public String sayHelloFromClientOne(String name) {
        return "Hi!"+name+"!Sorry,Error!";
    }

    @Override
    public String sayHiFromClientOne() {
        return "I'm in error";
    }
}
