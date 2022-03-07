package com.example.hello.exe.hello.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloConsumer {
    @RabbitListener(queues = "queue")
    public void consumer(String s){
        log.info("收到:{}",s);
    }
}
