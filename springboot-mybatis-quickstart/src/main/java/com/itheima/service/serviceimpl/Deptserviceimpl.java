package com.itheima.service.serviceimpl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.Deptservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Deptserviceimpl implements Deptservice {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class)  //事务管理
    @Override
    public void delete(Integer id) throws Exception {
        //根据id删除部门数据
        deptMapper.delete(id);

//        //模拟异常
//        if(true){
//            throw new Exception("出错了...");
//        }

        //调用员工层对象，传入id(部门id)删除下面的员工
        empMapper.deleteByDeptId(id);

    }

    @Override
    public void insert(Dept dept) {
        deptMapper.insert(dept);
    }

    @Override
    public Dept listid(Integer id) {
        return deptMapper.listid(id);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }

}
