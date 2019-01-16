package com.shgx.business.aspect;

import com.shgx.business.model.Message;
import com.shgx.business.service.MailSendService;
import com.shgx.business.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 应用服务异常切面
 * @auther guangxush
 * @create 2019-01-13
 */
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private MailSendService mailSendService;

    @Autowired
    private RabbitMqService rabbitMqService;


    @Pointcut("execution(* com.shgx.business.service.*.*(..))||execution(* com.shgx.business.controller.*.*(..))")
    private void log() {

    }

    /**
     * 捕获异常并把异常信息发送给用户
     * */
    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        String errMsg = "Errors " + e + " happened in AccioService: " + getMethodNameAndArgs(joinPoint);
        log.error(errMsg);
        mailSendService.sendEmail(errMsg);
        rabbitMqService.sendLogToMQ(new Message(errMsg));
    }

    /**
     * 反射获取类文件信息
     * */
    private String getMethodNameAndArgs (JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuffer sb = new StringBuffer();
        sb.append(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        for (int i = 0; i < args.length;  i++ ) {
            sb.append(" arg[" + i + "]: " + args[i]);
        }
        return sb.toString();
    }
}
