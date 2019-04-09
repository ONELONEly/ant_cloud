package com.gree.eureka_server.controller;

import org.springframework.stereotype.Controller;


@Controller
public class ServiceController {

//    private final Logger logger = LoggerFactory.getLogger(ServiceController.class);
//    private final RestTemplate restTemplate;
//    private final EurekaClient eurekaClient;
//
//    @Autowired
//    public ServiceController(RestTemplate restTemplate, EurekaClient eurekaClient) {
//        this.restTemplate = restTemplate;
//        this.eurekaClient = eurekaClient;
//    }

//    @RequestMapping(value = "/getMicroServices", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
//    public String testController1(){
//        StringBuilder sb = new StringBuilder();
//        Applications applications = eurekaClient.getApplications();
//
//        for(Application application : applications.getRegisteredApplications()) {
//            try {
//                //通过swagger查询服务接口的接口来获取该服务节点下的所有服务接口明细
//                String rsp = restTemplate.getForEntity("http://" + application.getName() + "/v2/api-docs", String.class).getBody();
//                //使用fastjson进行解析
//                JSONObject apiObject = JSON.parseObject(rsp);
//                JSONObject pathsObject = apiObject.getJSONObject("paths");
//                for (Map.Entry<String, Object> path : pathsObject.entrySet()) {
//                    System.out.println(path.getKey());
//                }
//                sb.append(rsp);
//            }catch(Exception ex){
//                //这里需要注意对于没有使用swagger的服务是无法调用swagger接口的，会抛出异常，需要对异常捕获后继续执行
//                continue;
//            }
//        }
//        return sb.toString();
//    }
}
