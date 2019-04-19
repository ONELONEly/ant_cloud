package com.gree.controller;

import com.gree.entity.vo.User;
import com.gree.result.RestResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class ChatServerControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new ChatServerController()).build();
    }

    @Test
    public void sayHello() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/sayHello?name=jinyu").
//                accept(MediaType.APPLICATION_JSON)).
//                andExpect(status().isOk()).
//                andExpect(content().string(equalTo("")));
        RestResponse restResponse = new RestResponse<>(new User(),null);
        System.out.println(restResponse.getResult().getClass());
    }
}