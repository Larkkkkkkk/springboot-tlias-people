package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.Empservice;
import com.itheima.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private Empservice empservice;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        Emp e=empservice.login(emp);
        //登陆成功  --添加令牌
        if(e!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id","1");
            claims.put("username","张三");
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        //登陆失败
        return e!=null?Result.success(e):Result.error("登录失败，没找到用户！");
    }

}
