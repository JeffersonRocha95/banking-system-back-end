package com.banking.clientesmicroservicios;

import com.banking.clientesmicroservicios.dto.ClienteDto;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
class ClienteIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    void debeCrearClienteCorrectamente() {

        ClienteDto dto = new ClienteDto();
        dto.setClienteId("C900");
        dto.setNombre("Cliente Test");
        dto.setGenero("M");
        dto.setEdad(25);
        dto.setIdentificacion("1234567899");
        dto.setDireccion("Direccion Test");
        dto.setTelefono("0999999999");
        dto.setContrasenia("1234");

        HttpEntity<ClienteDto> request = new HttpEntity<>(dto);

        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/clientes", request, String.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }
}
