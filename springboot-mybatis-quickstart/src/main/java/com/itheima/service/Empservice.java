package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface Empservice {


    PageBean page(Integer page, Integer pageSize);

    PageBean page2(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(Integer[] ids);

    void insert(Emp emp);

    Emp selectid(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
