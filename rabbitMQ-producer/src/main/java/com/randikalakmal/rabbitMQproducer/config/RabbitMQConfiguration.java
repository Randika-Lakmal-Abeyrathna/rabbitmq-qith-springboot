package com.randikalakmal.rabbitMQproducer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";

    @Bean
    Queue queueA(){
        return new Queue("Queue.A",false);
    }

    @Bean
    Queue queueB(){
        return new Queue("Queue.B",false);
    }

    @Bean
    Queue queueAll(){
        return new Queue("Queue.All",false);
    }

    @Bean
    Queue queueHeaderA(){
        return new Queue("Queue.HeaderA",false);
    }

    @Bean
    Queue queueHeaderB(){
        return new Queue("Queue.HeaderB",false);
    }

    @Bean
    Queue queueHeaderAll(){
        return new Queue("Queue.HeaderAll",false);
    }


    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("exchange.topic");
    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    HeadersExchange headersExchange(){
        return new HeadersExchange("exchange.header");
    }

    @Bean
    Binding bindingA(Queue queueA,DirectExchange exchange){
        return BindingBuilder.bind(queueA)
                .to(exchange)
                .with(ROUTING_A);
    }

    @Bean
    Binding bindingB(Queue queueB,DirectExchange exchange){
        return BindingBuilder.bind(queueB)
                .to(exchange)
                .with(ROUTING_B);
    }

    @Bean
    Binding bindingAWithFanOut(Queue queueA,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueA)
                .to(fanoutExchange);
    }

    @Bean
    Binding bindingBWithFanOut(Queue queueB,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueB)
                .to(fanoutExchange);
    }

    @Bean
    Binding bindingAWithTopic(Queue queueA,TopicExchange topicExchange){
        return BindingBuilder.bind(queueA)
                .to(topicExchange)
                .with(ROUTING_A);
    }

    @Bean
    Binding bindingBWithTopic(Queue queueB,TopicExchange topicExchange){
        return BindingBuilder.bind(queueB)
                .to(topicExchange)
                .with(ROUTING_B);
    }

    @Bean
    Binding bindingAllWithTopic(Queue queueAll,TopicExchange topicExchange){
        return BindingBuilder.bind(queueAll)
                .to(topicExchange)
                .with("routing.*");
    }


    @Bean
    Binding bindingAWithHeader(Queue queueHeaderA,HeadersExchange headersExchange){
        return BindingBuilder.bind(queueHeaderA)
                .to(headersExchange)
                .where("colour")
                .matches("red");
    }

    @Bean
    Binding bindingBWithHeader(Queue queueHeaderB,HeadersExchange headersExchange){
        return BindingBuilder.bind(queueHeaderB)
                .to(headersExchange)
                .where("colour")
                .matches("blue");
    }

    @Bean
    Binding bindingAllWithHeader(Queue queueHeaderAll,HeadersExchange headersExchange){
        return BindingBuilder.bind(queueHeaderAll)
                .to(headersExchange)
                .where("colour")
                .matches("green");
    }


    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate (ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
