package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.Empservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/emps")
public class Empcontroller {

    @Autowired
    private Empservice empservice;

    //员工列表查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "1")Integer pageSize){
        PageBean p= empservice.page(page,pageSize);
        return Result.success(p);
    }

    //员工列表查询
    @GetMapping("/all")
    public Result page2(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "1")Integer pageSize,
                        String name,
                        Short gender,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                        @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end){
        PageBean p= empservice.page2(page,pageSize,name,gender,begin,end);
        return Result.success(p);
    }

    //删除员工
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Integer[] ids){  //路径参数 所以要用@PathVariable注解
        empservice.delete(ids);
        return Result.success();
    }

    //新增员工
    @PostMapping
    public Result insert(@RequestBody Emp emp){  //json形式传入就要用@RequestBody  如果按顺序插入就可以自动封装
        empservice.insert(emp);
        return Result.success();
    }

    //修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp){
        empservice.update(emp);
        return Result.success();
    }

    //查询员工
    @GetMapping("/{id}")
    public Result selectid(@PathVariable Integer id){
        Emp emp=empservice.selectid(id);
        return Result.success(emp);
    }


}
