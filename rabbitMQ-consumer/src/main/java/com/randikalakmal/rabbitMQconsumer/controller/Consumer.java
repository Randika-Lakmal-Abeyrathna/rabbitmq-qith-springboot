package com.randikalakmal.rabbitMQconsumer.controller;

import com.randikalakmal.rabbitMQconsumer.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @RabbitListener(queues = "Queue.A")
    private void receiveFromQueueA(Message message){
        log.info("Message Received from QUEUE A --> {}",message);
    }

    @RabbitListener(queues = "Queue.B")
    private void receiveFromQueueB(Message message){
        log.info("Message Received from QUEUE B --> {}",message);
    }

    @RabbitListener(queues = "Queue.All")
    private void receiveFromQueueAll(Message message){
        log.info("Message Received from QUEUE All --> {}",message);
    }


    @RabbitListener(queues = "Queue.HeaderA")
    private void receiveFromQueueHeaderA(String message){
        log.info("Message Received from QUEUE Header A --> {}",message);
    }

    @RabbitListener(queues = "Queue.HeaderB")
    private void receiveFromQueueHeaderB(String message){
        log.info("Message Received from QUEUE Header B --> {}",message);
    }

    @RabbitListener(queues = "Queue.HeaderAll")
    private void receiveFromQueueHeaderAll(String message){
        log.info("Message Received from QUEUE Header All --> {}",message);
    }

}
