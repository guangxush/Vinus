package com.shgx.show.service.impl;

import com.shgx.show.model.Message;
import com.shgx.show.repository.MessageRepo;
import com.shgx.show.service.ShowMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @auther guangxush
 * @create 2019/1/15
 */
@Service
public class ShowMessageServiceImpl implements ShowMessageService {

    @Autowired
    private MessageRepo messageRepo;

    /**
     * 展示所有的消息内容
     * @return
     */
    @Override
    public List<Message> showAllMessage(){
        Optional<List<Message>> messages = Optional.ofNullable(messageRepo.findAll());
        if(messages.isPresent()){
            return messages.get();
        }
        return null;
    }
}
