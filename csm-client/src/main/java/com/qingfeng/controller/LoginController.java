package com.qingfeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName LoginController
 * @author Administrator
 * @version 1.0.0
 * @Description 模拟登录验证
 * @createTime 2022/4/30 0030 18:23
 */
@Controller
public class LoginController {

    /**
     * @title check
     * @description 模拟登录验证
     * @author Administrator
     * @updateTime 2022/4/30 0030 18:24
     */
    @PostMapping("/check")
    @ResponseBody
    public Map<String,Object> check(@RequestParam(required = true) String username, @RequestParam(required = true) String password){
        Map<String,Object> result = new HashMap<>(1);
        System.out.println(MessageFormat.format("用户名：{0}，密码：{1}",username,password));

        //模拟登录
        System.out.println("模拟登录流程");
        result.put("code",200);

        return result;
    }


}
