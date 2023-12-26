package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.Deptservice;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private Deptservice deptservice;

    //private static Logger log= LoggerFactory.getLogger(DeptController.class);  直接在类上添加@Slf4j注解(注解内部会自动生成这行)

    //查询部门列表  --可能是多行结果
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> data=deptservice.list();

        return Result.success(data);
    }

    //删除部门
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        deptservice.delete(id);
        return Result.success();
    }

    //新增部门
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        deptservice.insert(dept);
        return Result.success();
    }

    //根据id查询部门 --只有一行
    @GetMapping("/{id}")
    public Result listid(@PathVariable Integer id){
        Dept dept=deptservice.listid(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptservice.update(dept);
        return Result.success();
    }

}
