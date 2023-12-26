package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  //组合注解=@ControllerAdvice + @ResponseBody(像controller层一样组合注解里面有这个，就可以自动将return返回的对象转为json字符串)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  //捕获异常类型
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("操作失败，请联系管理员");
    }

}
