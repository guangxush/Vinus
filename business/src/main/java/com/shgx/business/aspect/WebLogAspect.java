package com.shgx.business.aspect;

import com.shgx.business.model.Message;
import com.shgx.business.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 用户登录切面
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
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("login response: " + ret);
        logger += " and login response: " + ret;
        //给MQ发送用户登录的行为信息
        rabbitMqService.sendUserBehaviorToMQ(new Message(logger));
        logger = "";
    }
}
