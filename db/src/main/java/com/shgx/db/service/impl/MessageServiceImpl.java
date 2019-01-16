package com.shgx.db.service.impl;

import com.shgx.db.model.Message;
import com.shgx.db.repository.MessageRepo;
import com.shgx.db.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue}")
    private String queue;

    @Autowired
    private MessageRepo messageRepo;
    /**
     * 保存消息
     * @param exchange
     * @param queueName
     * @param messageValue
     * @return
     */
    @Override
    public Boolean saveMessage(String exchange, String queueName, String messageValue){
        Message message = Message.builder()
                .exchange(exchange)
                .queueName(queueName)
                .messageValue(messageValue)
                .saveDate(new Date())
                .build();
        try{
            messageRepo.save(message);
        }catch (Exception e){
            log.error("save the message {} failed!", messageValue);
            return false;
        }
        return true;
    }

    /**
     * 主动接收消息并保存在数据库中
     * @param messageValue
     */
    @RabbitListener(queues="log")//监听器监听指定的Queue
    @Override
    public void receive(String messageValue) {
        Message message = Message.builder()
                .exchange(exchange)
                .queueName(queue)
                .messageValue(messageValue)
                .saveDate(new Date())
                .build();
        try{
            messageRepo.save(message);
        }catch (Exception e){
            log.error("save the message {} failed!", messageValue);
        }
    }

}
