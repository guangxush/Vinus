package com.shgx.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther  guangxush
 * @create  2019-01-12
 */
@Component
public class MessageConsumer {
//    @RabbitListener(queues="topic.message")//监听器监听指定的Queue
//    public void process1(String str) {
//        System.out.println("message:"+str);
//    }
//    @RabbitListener(queues="topic.messages")//监听器监听指定的Queue
//    public void process2(String str) {
//        System.out.println("messages:"+str);
//    }
}
