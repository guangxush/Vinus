package com.shgx.show.controller;

import com.shgx.show.model.Message;
import com.shgx.show.service.ShowMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @auther guangxush
 * @create 2019/1/15
 */
@RestController
public class ShowMessageController {

    @Autowired
    private ShowMessageService showMessageService;

    @RequestMapping(value = "/mess/all", method = RequestMethod.GET)
    public List<Message> sendMessage(){
        return showMessageService.showAllMessage();
    }
}
