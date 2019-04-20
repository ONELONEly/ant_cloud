package com.gree.controller;

import com.alibaba.fastjson.JSON;
import com.gree.entity.po.User;
import com.gree.result.HandleRestResponse;
import com.gree.result.RestResponse;
import com.gree.redisService.AuthTokenApi;
import com.gree.redisService.ScheduleService;
import com.gree.util.UserContext;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * The type Say hello controller.
 *
 * @Description TODO
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -04-09 09:52:01
 * @Version V 1.0
 */
@RestController
@Api(value = "/",description = "打招呼的接口")
@RequestMapping("/")
public class SayHelloController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final ScheduleService scheduleService;

    @Autowired
    private AuthTokenApi authTokenApi;

    @Autowired
    public SayHelloController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @GetMapping("sayHello")
    @ApiOperation(value = "对用户说Hello",notes = "返回用户的招呼信息")
    @ApiImplicitParams({
            /*paramType:
            header->请求参数放置于requestHeader,使用@RequestHeader访问
            query->请求参数放置于请求地址，使用@RequestParam访问,
            path->请求参数放置于url连接，使用@PathVeriable访问,
            body,form->不常用*/
            @ApiImplicitParam(paramType = "query",name = "name",value = "用户名",dataType = "String",required = true)/*defaultValue = ""*/
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "${obj}",response = String.class)
    })
    public String sayHello(@RequestParam String name){
        return scheduleService.sayHelloFromClientOne(name);
    }/*@ApiParam用于描述该API操作接收的参数类型，value用于描述参数，required指明参数是否为必须,放置于@RequestParam之前*/

    @GetMapping("/hi")
    public String hi(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        logger.info("contextData: {},sessionId:{}", JSON.toJSONString(UserContext.contextData),uid);
        RestResponse restResponse = authTokenApi.checkToken("9c858595-a25f-4708-b3bb-1a78b065a2d4","/oauth/token");
        if (restResponse.getErrorResponse() == null){
            Map user = new HandleRestResponse<Map>().handle(Map.class,restResponse);
        }
        return scheduleService.sayHiFromClientOne();
    }

    @PostMapping("saveUser")
    @ResponseBody
    @ApiModelProperty(value = "user",notes = "用户信息的Json串")
    @ApiOperation(value = "新增用户",notes = "返回添加的用户信息")
    public ResponseEntity<Object> saveUser(@RequestBody User user){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
