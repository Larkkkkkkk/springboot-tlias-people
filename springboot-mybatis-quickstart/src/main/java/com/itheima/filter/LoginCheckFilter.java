package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        //1.获取请求url
        String url = request.getRequestURI().toString();
        log.info("请求的url:",url);

        //2.判断请求url中是否够包含login，如果包含就是登录操作放行
        if(url.contains("login")){
            log.info("登录操作，放行....");
            filterChain.doFilter(request,response);
            return;
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
            return;
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
            return;
        }
        //6.放行
        log.info("令牌合法，直接执行");
        filterChain.doFilter(request,response);

    }

}
