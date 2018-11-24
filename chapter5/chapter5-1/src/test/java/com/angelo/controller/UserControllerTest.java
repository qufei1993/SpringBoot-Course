package com.angelo.controller;

import com.angelo.domain.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 查询用户列表
     * @throws Exception
     */
    @Test
    public void userListTest() throws Exception {

        String url = "/user/list/false";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        System.out.println(response.getStatus()); // 获取响应状态码
        System.out.println(response.getContentAsString()); // 获取响应内容
    }

    /**
     * 创建测试用户
     * @throws Exception
     */
    @Test
    public void userAddTest() throws Exception {
        User user = new User();
        user.setName("测试姓名");
        user.setAge(22);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(user))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        System.out.println(response.getStatus()); // 获取响应状态码
        System.out.println(response.getContentAsString()); // 获取响应内容
    }

    /**
     * 根据id删除用户测试
     * @throws Exception
     */
    @Test
    public void deleteUserByIdTest() throws Exception {
        Integer id = 11;

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}