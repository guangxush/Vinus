package com.shgx.business.service;

import com.shgx.business.model.Message;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
public interface RabbitMqService {
    /**
     * 发送用户行为数据到MQ
     * @param message
     * @return
     */
    Boolean sendUserBehaviorToMQ(Message message);

    /**
     * 发送日志信息到Redis,临时存储
     * @param message
     * @return
     */
    Boolean sendLogToMQ(Message message);
}
