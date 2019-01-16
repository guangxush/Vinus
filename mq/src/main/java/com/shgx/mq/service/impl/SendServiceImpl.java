package com.shgx.mq.service.impl;

import com.shgx.mq.model.Message;
import com.shgx.mq.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Service
@Slf4j
public class SendServiceImpl implements SendService {

    @Autowired
    private AmqpTemplate template;

    /**
     * 发送消息到Rabbit MQ
     * @param message
     * @return
     */
    @Override
    public Boolean send(Message message) {
        String exchange = message.getExchange();
        String queueName = message.getQueueName();
        String messageValue = message.getMessageValue();
        try{
            template.convertAndSend(exchange,queueName,messageValue);
            log.info("send {},{},{} to mq success!!",exchange,queueName,messageValue);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
