package com.example.hello.exe.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息生产工具类
 */
@Component
@Slf4j
public class MqProducerUtils {
    @Autowired
    AmqpTemplate amqpTemplate;

    /**
     *
     * @param msg
     * @param topicName
     */
    public void sendMessage(String msg,String topicName){

        amqpTemplate.convertAndSend(topicName,msg);
        log.info("sendMessage发送消息成功！ topicName：{},msg:{}",topicName,msg);
    }
}
