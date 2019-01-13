package com.shgx.mq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-12
 */
@Component
public class MessageProducer {
    @Autowired
    private AmqpTemplate template;

    public void send() {
        template.convertAndSend("queue","hello,rabbit~");
    }

    public void send2(){
        template.convertAndSend("exchange","topic.message","hello, rabbit!");
    }
}
