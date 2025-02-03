package com.alertas_historico.alertas_historico_productor.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.alertas_historico.alertas_historico_productor.model.SignosVitales;

import org.springframework.beans.factory.annotation.Value;
@Service
public class RabbitMQService {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.alertas}")
    private String historicoExchange;

    @Value("${rabbitmq.routingkey.alertas_historico}")
    private String historicoRoutingKey;

    public RabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarHistorial(SignosVitales signosVitales) {
        rabbitTemplate.convertAndSend(historicoExchange, historicoRoutingKey, signosVitales);
        System.out.println("Signos vitales para historial enviados a RabbitMQ: " + signosVitales);
    }
}
