package com.shgx.mq.service;

import com.shgx.mq.model.Message;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
public interface RedisSaveService {
    /**
     * 通过channel获取信息
     * @param channel
     * @param message
     * @return
     */
    Boolean sendChannelMess(String channel, String message);

    /**
     * 通过list获取信息
     * @param message
     * @return
     */
    Boolean sendListMess(String message);
}
