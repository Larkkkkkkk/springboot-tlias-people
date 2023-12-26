package com.itheima.service.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.Empservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class Empserviceimpl implements Empservice {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1.获取总记录数
        //Long count=empMapper.count();
        //2.获取分页查询结果列表
        //Integer start=(page-1)*pageSize;
        //List<Emp> empList= empMapper.page(start, pageSize);  //计算start位置
        //3.封装PageBean对象
        //PageBean pageBean=new PageBean(count,empList);


        //PageHelper插件
        //1和2步骤省略了
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<Emp> list = empMapper.list();
        Page<Emp> p= (Page<Emp>) list;
        //3.封装PageBean对象
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public PageBean page2(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<Emp> list = empMapper.list2(page,pageSize,name,gender,begin,end);
        Page<Emp> p= (Page<Emp>) list;
        //3.封装PageBean对象
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(Integer[] ids) {
        empMapper.delete(ids);
    }

    @Override
    public void insert(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    public Emp selectid(Integer id) {
        return empMapper.selectid(id);
    }

    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUserNameAndPassWord(emp);
    }


}
