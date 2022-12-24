package com.randikalakmal.rabbitMQproducer.controller;


import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeaderExchangeController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    HeadersExchange headersExchange;

    @GetMapping("/Header/{name}")
    public String sendA(@PathVariable(value = "name") String name){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("colour",name);
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message1 = messageConverter.toMessage(name,messageProperties);
        rabbitTemplate.send(headersExchange.getName(),"",message1);

        return "Message sent successfully via Header exchange";
    }

}
