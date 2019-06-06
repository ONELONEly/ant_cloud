package com.gree.serviceApi;

import com.gree.serviceApi.hystric.ScheduleServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client-say-hi",fallback = ScheduleServiceHystric.class)
@Component
public interface ScheduleService {
    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    String sayHelloFromClientOne(@RequestParam(value = "name")String name);

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne();
}
