package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //在运行时,会自动生成该接口的实现类对象(代理对象), 并且将该对象交给IOC容器管理
public interface DeptMapper {
    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values (#{name},now(),now())")
    void insert(Dept dept);

    @Select("select * from dept where id=#{id}")
    Dept listid(Integer id);

    @Update("update dept set name=#{name},update_time=now() where id=#{id}")
    void update(Dept dept);
}
