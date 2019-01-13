package com.shgx.business.service.impl;

import com.shgx.business.model.Message;
import com.shgx.business.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Service
@Slf4j
public class RabbitMqServiceImpl  implements RabbitMqService {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue}")
    private String queue;

    @Value("${rabbitmq.url}")
    private String url;

    @Autowired
    private RestTemplateBuilder builder;

    @Override
    public Boolean sendLogToMQ(Message message){
        message.setExchange(exchange);
        message.setQueueName(queue);
        JSONObject messageJson = JSONObject.fromObject(message);
        RestTemplate restTemplate = builder.build();
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
            log.error("send the message failed!");
            return false;
        }
    }
}
