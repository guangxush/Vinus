package com.shgx.mq.model;


import lombok.Data;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Data
public class Message {

    private String exchange;
    private String queueName;
    private String message;

}
