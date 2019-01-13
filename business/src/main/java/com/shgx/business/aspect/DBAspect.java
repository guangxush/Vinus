package com.shgx.business.aspect;

import com.shgx.business.model.Message;
import com.shgx.business.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Aspect
@Slf4j
public class DBAspect {

    @Autowired
    private RabbitMqService rabbitMqService;

    @Pointcut("execution(* com.shgx.business.respository.*.*(..))")
    private void db() {

    }

    @AfterReturning(returning = "ret", pointcut = "db()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("login response: " + ret);
        String logger = " and login response: " + ret;
        //给MQ发消息
        rabbitMqService.sendLogToMQ(new Message(logger));
    }
}
