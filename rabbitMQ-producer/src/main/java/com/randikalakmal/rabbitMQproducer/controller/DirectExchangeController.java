package com.randikalakmal.rabbitMQproducer.controller;

import com.randikalakmal.rabbitMQproducer.model.Message;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectExchangeController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @PostMapping("/DirectPost/A")
    public String sendA(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(),"routing.A",message);
        return "Message Sent Successfully to A";
    }
    @PostMapping("/DirectPost/B")
    public String sendB(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(),"routing.B",message);
        return "Message Sent Successfully to B";
    }
}
