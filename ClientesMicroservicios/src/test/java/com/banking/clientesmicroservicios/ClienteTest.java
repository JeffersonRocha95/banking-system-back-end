package com.banking.clientesmicroservicios;

import static org.junit.jupiter.api.Assertions.*;

import com.banking.clientesmicroservicios.entity.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    @DisplayName("Prueba unitaria creacion cliente heredando datos de persona")
    void debeCrearClienteConDatosDePersona() {

        // given
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("María López");
        cliente.setGenero("F");
        cliente.setEdad(30);
        cliente.setIdentificacion("1102456789");
        cliente.setDireccion("Av. Siempre Viva 742");
        cliente.setTelefono("0987654321");
        cliente.setClienteId("C001");
        cliente.setContrasenia("12345");
        cliente.setEstado(true);

        // then
        assertNotNull(cliente);

        // Persona
        assertEquals(1L, cliente.getId());
        assertEquals("María López", cliente.getNombre());
        assertEquals("F", cliente.getGenero());
        assertEquals(30, cliente.getEdad());
        assertEquals("1102456789", cliente.getIdentificacion());
        assertEquals("Av. Siempre Viva 742", cliente.getDireccion());
        assertEquals("0987654321", cliente.getTelefono());

        // Cliente
        assertEquals("C001", cliente.getClienteId());
        assertEquals("12345", cliente.getContrasenia());
        assertTrue(cliente.getEstado());
    }
}