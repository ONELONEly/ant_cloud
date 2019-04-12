package com.gree.service.serviceImp;

import com.gree.service.ScheduleService;
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
