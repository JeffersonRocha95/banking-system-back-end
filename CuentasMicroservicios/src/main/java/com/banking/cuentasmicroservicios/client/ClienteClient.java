package com.banking.cuentasmicroservicios.client;

import com.banking.cuentasmicroservicios.dto.ClienteDto;
import com.banking.cuentasmicroservicios.exception.RecursoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteClient {

    private final RestTemplate restTemplate;

    private static final String CLIENTES_URL =
            "http://localhost:8081/api/clientes/{id}";

    public ClienteClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClienteDto obtenerCliente(Long clienteId) {

        try {
            return restTemplate.getForObject(
                    CLIENTES_URL,
                    ClienteDto.class,
                    clienteId
            );

        } catch (HttpClientErrorException ex) {

            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RecursoNoEncontradoException(
                        "Cliente no encontrado con id: " + clienteId
                );
            }

            throw new RuntimeException(
                    "Error al consultar microservicio de clientes",
                    ex
            );

        } catch (RestClientException ex) {
            throw new RuntimeException(
                    "No se pudo conectar con microservicio de clientes",
                    ex
            );
        }
    }
}
