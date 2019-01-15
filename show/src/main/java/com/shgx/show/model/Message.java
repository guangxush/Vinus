package com.shgx.show.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description
 * @auther guangxush
 * @create 2019/1/15
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {
    /**
     * 自增主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 路由器
     */
    @Column(name = "exchange")
    private String exchange;
    /**
     * 消息队列名称
     */
    @Column(name = "queue_name")
    private String queueName;

    /**
     * 消息内容
     */
    @Column(name = "message_value")
    private String messageValue;

    /**
     * 插入时间
     */
    @Column(name = "save_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saveDate;
}

