package com.shgx.db.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Data
@Builder
public class MessageVO {

    private String exchange;
    private String queueName;
    private String messageValue;
}
