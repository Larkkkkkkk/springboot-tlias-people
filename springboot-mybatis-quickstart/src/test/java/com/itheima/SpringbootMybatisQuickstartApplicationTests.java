package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest //springboot整合单元测试注解
class SpringbootMybatisQuickstartApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testListUser(){
        List<User> userList = userMapper.list();
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void testJwt(){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id","1");
        claims.put("username","张三");


        String sign = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
                .sign(Algorithm.HMAC256("itheima"));

        System.out.println(sign);
    }


}
