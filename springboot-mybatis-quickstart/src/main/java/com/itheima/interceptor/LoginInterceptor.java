package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component  //放入ioc容器(不属于三大类，所以用component注解)
public class LoginInterceptor implements HandlerInterceptor {

    @Override //目标资源方法运行前执行  --返回true就放行 返回false就不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求的url:",url);

        //2.判断请求url中是否够包含login，如果包含就是登录操作放行
        if(url.contains("login")){
            log.info("登录操作，放行....");
            return true;
        }

        //3.判断请求头中的令牌
        String token = request.getHeader("token");

        //4.判断是否存在  不存在返回未登录
        if(!StringUtils.hasLength(token)){  //不存在
            log.info("请求头token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //对象->json  使用阿里巴巴的fastjson
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return false;
        }

        //5.解析token  解析失败返回未登录
        try {
            JwtUtil.parseToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回登录错误的信息");
            Result error = Result.error("NOT_LOGIN");
            //对象->json  使用阿里巴巴的fastjson
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return false;
        }
        //6.放行
        log.info("令牌合法，直接执行");
        return true; //放行
    }

    @Override //目标资源方法运行后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle方法");
    }

    @Override //视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion方法");
    }
}
