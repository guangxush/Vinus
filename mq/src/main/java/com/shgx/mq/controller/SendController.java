package com.shgx.mq.controller;

import com.shgx.mq.model.Message;
import com.shgx.mq.service.RedisSaveService;
import com.shgx.mq.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@RestController
public class SendController {

    @Autowired
    private SendService sendService;

    @Autowired
    private RedisSaveService redisSaveService;

    @Value("${listener.channel}")
    private String channel;


    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Boolean sendMessage(@RequestBody Message message){
        return sendService.send(message);
    }

    @RequestMapping(value = "/send/redis", method = RequestMethod.POST)
    public Boolean sendMessageInRedis(@RequestBody Message message){
        return redisSaveService.sendChannelMess(channel, message.getMessageValue());
    }
}
