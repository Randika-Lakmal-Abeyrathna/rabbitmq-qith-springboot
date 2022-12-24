package com.randikalakmal.rabbitMQproducer.controller;

import com.randikalakmal.rabbitMQproducer.model.Message;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanoutExchangeController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    FanoutExchange exchange;

    @PostMapping("/FanOut/Post")
    public String send(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(),"",message);
        return "Message sent successfully via fan out exchange";
    }
}
