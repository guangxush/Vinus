package com.shgx.mq.service;

/**
 * @Description redis
 * @auther guangxush
 * @create 2019-01-13
 */
public interface RedisSendDataScheduled {

    void receiveMessage(String message);
}
