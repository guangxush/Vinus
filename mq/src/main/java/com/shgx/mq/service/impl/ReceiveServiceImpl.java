package com.shgx.mq.service.impl;

import com.shgx.mq.model.Message;
import com.shgx.mq.service.ReceiveService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Service
@Slf4j
@Component
public class ReceiveServiceImpl implements ReceiveService {

    @Autowired
    private RestTemplateBuilder builder;

    @Value("${db.url}")
    private String url;

    private static final String DEFAULT_MESSAGE = "default message";


    //暂时先关闭对于RabbitMQ的监听，如果想主动发送请求保存数据可以打开
    //@RabbitListener(queues="topic.message")//监听器监听指定的Queue
    @Override
    public void process(String messageName) {
        System.out.println("message:"+messageName);
        Message message = new Message();
        message.setExchange("exchange");
        message.setQueueName("topic.message");
        if (messageName == null) {
            message.setQueueName(DEFAULT_MESSAGE);
        }
        message.setQueueName(messageName);
        persistenceMessage(message);
    }

    public Boolean persistenceMessage(Message message){
        JSONObject messageJson = JSONObject.fromObject(message);
        RestTemplate restTemplate = builder.build();
        //String url = "http://localhost:8082/db/save";
        Boolean saveResult = false;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(messageJson.toString(), headers);
        try{
            ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url,formEntity,Boolean.class);
            if(responseEntity.getStatusCode() == HttpStatus.OK){
                saveResult = responseEntity.getBody();
            }
            return saveResult;
        }catch (Exception e){
            log.error("save the message failed!");
            return false;
        }
    }
}
