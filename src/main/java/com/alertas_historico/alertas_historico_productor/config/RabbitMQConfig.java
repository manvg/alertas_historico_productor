package com.alertas_historico.alertas_historico_productor.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.alertas}")
    private String historicoExchange;

    @Value("${rabbitmq.queue.alertas_historico}")
    private String historicoQueue;

    @Value("${rabbitmq.routingkey.alertas_historico}")
    private String historicoRoutingKey;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(historicoExchange);
    }

    @Bean
    public Queue historicoQueue() {
        return new Queue(historicoQueue, true);
    }

    @Bean
    public Binding bindingHistorico(Queue historicoQueue, DirectExchange exchange) {
        return BindingBuilder.bind(historicoQueue).to(exchange).with(historicoRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}