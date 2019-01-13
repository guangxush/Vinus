package com.shgx.business.aspect;

import com.shgx.business.model.Message;
import com.shgx.business.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(* com.shgx.business.controller.UserController.*(..))")
    public void webLog(){}

    private String logger = "";

    @Autowired
    private RabbitMqService rabbitMqService;

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("IP : " + request.getRemoteAddr());
        logger += "ip:" + request.getRemoteAddr();
        //这个地方不安全
        //log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("login response: " + ret);
        logger += " and login response: " + ret;
        //给MQ发消息
        rabbitMqService.sendLogToMQ(new Message(logger));
        logger = "";
    }

}
