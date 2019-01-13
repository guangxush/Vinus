package com.shgx.db.service;

import com.shgx.db.model.Message;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
public interface MessageService {
    /**
     * 保存消息
     * @param exchange
     * @param queueName
     * @param messageValue
     * @return
     */
    Boolean saveMessage(String exchange, String queueName, String messageValue);

    /**
     * 监听消息并处理
     * @param messageName
     * @return
     */
    void receive(String messageName);
}
