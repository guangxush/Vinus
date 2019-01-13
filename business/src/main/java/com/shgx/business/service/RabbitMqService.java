package com.shgx.business.service;

import com.shgx.business.model.Message;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
public interface RabbitMqService {
    Boolean sendLogToMQ(Message message);
}
