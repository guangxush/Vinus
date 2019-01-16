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

    /**
     * 数据存储在mq模块下的rabbit mq中
     */
    @Value("${rabbitmq.url}")
    private String rabbitUrl;

    /**
     * 数据存储在mq模块下的redis
     */
    @Value("${rabbitmq.redis.url}")
    private String redisUrl;

    @Autowired
    private RestTemplateBuilder builder;

    /**
     * 发送用户行为数据到MQ，即时处理
     * @param message
     * @return
     */
    @Override
    public Boolean sendUserBehaviorToMQ(Message message){
        message.setExchange(exchange);
        message.setQueueName(queue);
        return requestUrl(rabbitUrl, message);
    }

    /**
     * 发送日志信息到Redis,临时存储, 隔一段时间后在处理
     * @param message
     * @return
     */
    @Override
    public Boolean sendLogToMQ(Message message){
        return requestUrl(redisUrl, message);
    }

    /**
     * 发送URL请求
     * @param url
     * @param message
     * @return
     */
    private Boolean requestUrl(String url, Message message){
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
