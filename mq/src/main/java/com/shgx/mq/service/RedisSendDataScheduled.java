package com.shgx.mq.service;

/**
 * @Description redis
 * @auther guangxush
 * @create 2019-01-13
 */
public interface RedisSendDataScheduled {

    /**
     * 收到通道的消息之后执行的方法
     * @param message
     */
    void receiveMessage(String message);

    /**
     * redis定时发送数据
     */
    void popListMessage();
}
