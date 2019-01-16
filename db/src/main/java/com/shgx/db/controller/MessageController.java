package com.shgx.db.controller;

import com.shgx.db.model.MessageVO;
import com.shgx.db.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MessageController {
    private static final String EXCHANGE = "DEFAULT";
    private static final String QUEUE_NAME = "DEFAULT_QUEUE";

    @Autowired
    private MessageService messageService;

    /**
     * 保存日志信息到数据库中
     * @param message
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Boolean sendMessage(@RequestBody MessageVO message){
        String exchange = message.getExchange();
        if(exchange==null){
            exchange = EXCHANGE;
        }
        String queueName = message.getQueueName();
        if(queueName==null){
            queueName=QUEUE_NAME;
        }
        String messageValue = message.getMessageValue();
        return messageService.saveMessage(exchange, queueName, messageValue);
    }
}
