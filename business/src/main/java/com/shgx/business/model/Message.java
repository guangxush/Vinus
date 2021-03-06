package com.shgx.business.model;

import lombok.Data;

/**
 * @Description 消息处理
 * @auther guangxush
 * @create 2019-01-13
 */
@Data
public class Message {

    private String exchange;
    private String queueName;
    private String messageValue;

    public Message(String messageValue){
        this.messageValue = messageValue;
    }

}
