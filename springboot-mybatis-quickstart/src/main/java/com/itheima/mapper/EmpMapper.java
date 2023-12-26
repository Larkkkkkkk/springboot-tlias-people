package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper //在运行时,会自动生成该接口的实现类对象(代理对象), 并且将该对象交给IOC容器管理
public interface EmpMapper {
  //原始分页查询
    //查询总记录数
    //@Select("select count(*) from emp")
    //public Long count();

    //条件查询某页的多条信息
    //@Select("select * from emp limit #{start},#{pageSize}")
    //public List<Emp> page(Integer start, Integer pageSize);

  //pagehelper插件
      @Select("select * from emp")
      public List<Emp> list();

    //xml文件映射
    //@Select("select * from emp where name like concat('%',#{name},'%') and gender=#{gender} and entrydate between #{begin} and #{end}")
    List<Emp> list2(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    //删除员工
    void delete(Integer[] ids);

    //新增员工
    @Insert("insert into emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time) values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},now(),now())")
    void insert(Emp emp);

    //查询员工
    @Select("select * from emp where id=#{id}")
    Emp selectid(Integer id);

    //修改员工
    void update(Emp emp);

    //查找员工的账号和密码
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByUserNameAndPassWord(Emp emp);

    //根据部门id删除该部门下所有员工
    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer deptId);

}

