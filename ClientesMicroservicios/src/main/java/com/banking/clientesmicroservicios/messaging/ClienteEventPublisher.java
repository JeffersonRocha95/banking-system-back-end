package com.banking.clientesmicroservicios.messaging;

import com.banking.clientesmicroservicios.config.RabbitConfig;
import com.banking.clientesmicroservicios.entity.Cliente;
import com.banking.clientesmicroservicios.event.ClienteCreadoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClienteEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public ClienteEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarClienteCreado(Cliente guardado) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                guardado
        );
    }
}