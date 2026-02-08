package com.banking.cuentasmicroservicios.messaging;

import com.banking.cuentasmicroservicios.config.RabbitConfig;
import com.banking.cuentasmicroservicios.event.ClienteCreadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ClienteEventListener {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void recibirClienteCreado(ClienteCreadoEvent event)
    {
        System.out.println(
                "Evento ASINCRONO recibido en CUENTAS - Cliente ID: "
                        + event.getId()
        );
    }
}