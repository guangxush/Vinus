package com.shgx.mq.service.impl;

import com.shgx.mq.model.Message;
import com.shgx.mq.service.SendService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private AmqpTemplate template;

    @Override
    public Boolean send(Message message) {
        String exchange = message.getExchange();
        String queueName = message.getQueueName();
        String messageValue = message.getMessageValue();
        try{
            template.convertAndSend(exchange,queueName,messageValue);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
