package com.itheima.service.serviceimpl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Userserviceimpl implements Userservice {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }
}
