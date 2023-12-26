package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface Deptservice {
    List<Dept> list();

    void delete(Integer id) throws Exception;

    void insert(Dept dept);

    Dept listid(Integer id);

    void update(Dept dept);
}
