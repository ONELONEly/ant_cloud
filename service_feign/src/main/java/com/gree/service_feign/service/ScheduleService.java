package com.gree.service_feign.service;

import com.gree.service_feign.service.serviceImp.ScheduleServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client-say-hi",fallback = ScheduleServiceHystric.class)
@Component
public interface ScheduleService {
    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name")String name);
}
