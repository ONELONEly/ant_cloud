package com.gree.controller;

import com.gree.entity.po.UserPO;
import com.gree.serviceApi.AuthTokenApi;
import com.gree.serviceApi.ScheduleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("saveUser")
    @ResponseBody
    @ApiModelProperty(value = "user",notes = "用户信息的Json串")
    @ApiOperation(value = "新增用户",notes = "返回添加的用户信息")
    public ResponseEntity<Object> saveUser(@RequestBody UserPO userPO){
        return new ResponseEntity<>(userPO, HttpStatus.OK);
    }


}
