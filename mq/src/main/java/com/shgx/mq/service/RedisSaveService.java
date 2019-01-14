package com.shgx.mq.service;

import com.shgx.mq.model.Message;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
public interface RedisSaveService {
    Boolean sendChannelMess(String channel, String message);
}
