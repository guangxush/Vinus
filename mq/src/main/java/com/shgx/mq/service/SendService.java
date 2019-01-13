package com.shgx.mq.service;

import com.shgx.mq.model.Message;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
public interface SendService {
    /**
     * 发送日志
     * @param message
     */
    Boolean send(Message message);
}
