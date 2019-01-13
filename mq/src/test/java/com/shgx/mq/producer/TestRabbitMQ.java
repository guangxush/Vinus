package com.shgx.mq.producer;

import com.shgx.mq.MqApplication;
import com.shgx.mq.consumer.MessageConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-12
 */
@SpringBootTest(classes= MqApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {
    @Autowired
    private MessageProducer producer;

    @Test
    public void testRabbit() {
        producer.send();
    }

    @Test
    public void testRabbit2() {
        producer.send2();
    }

}
