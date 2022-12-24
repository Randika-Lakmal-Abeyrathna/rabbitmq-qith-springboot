package com.randikalakmal.rabbitMQproducer.controller;

import com.randikalakmal.rabbitMQproducer.model.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicExchangeController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TopicExchange topicExchange;

    @PostMapping("/Topic/A")
    public String sendA(@RequestBody Message message){
        rabbitTemplate.convertAndSend(topicExchange.getName(),"routing.A",message);
        return "Message sent successfully via topic exchange";
    }

    @PostMapping("/Topic/B")
    public String sendB(@RequestBody Message message){
        rabbitTemplate.convertAndSend(topicExchange.getName(),"routing.B",message);
        return "Message sent successfully via topic exchange";
    }

}
