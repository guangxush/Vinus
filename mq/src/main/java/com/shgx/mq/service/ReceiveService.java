package com.shgx.mq.service;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
public interface ReceiveService {
    /**
     * 监听消息并处理
     * @param messageName
     * @return
     */
    void process(String messageName);
}
