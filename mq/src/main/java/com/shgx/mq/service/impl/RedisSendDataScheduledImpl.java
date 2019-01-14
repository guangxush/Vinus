package com.shgx.mq.service.impl;

import com.shgx.mq.service.ReceiveService;
import com.shgx.mq.service.RedisSaveService;
import com.shgx.mq.service.RedisSendDataScheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @auther guangxush
 * @create 2019/1/14
 */
@Service
//@EnableScheduling
public class RedisSendDataScheduledImpl implements RedisSendDataScheduled {

    @Autowired
    RedisSaveService redisSaveService;

    @Autowired
    ReceiveService receiveService;

    /**
     * 收到通道的消息之后执行的方法
     * @param message
     */
    @Override
    //@Scheduled(fixedRate = 1000 * 60 * 2)
    public void receiveMessage(String message) {
        System.out.println("receive the message is: "+message);
        receiveService.process(message);
    }

}
