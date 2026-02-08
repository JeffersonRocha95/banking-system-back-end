package com.banking.clientesmicroservicios.controller;

import com.banking.clientesmicroservicios.dto.ClienteDto;
import com.banking.clientesmicroservicios.service.interfaces.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/clientes")
public class ClienteController  {
    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDto> CrearCliente(@RequestBody ClienteDto dto) {
        ClienteDto creado = clienteService.CrearCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> ListarClientes() {
        return ResponseEntity.ok(clienteService.ListarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> ObtenerClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.ObtenerClientePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> ActualizarCliente(
            @PathVariable Long id,
            @RequestBody ClienteDto dto
    ) {
        return ResponseEntity.ok(clienteService.ActualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> EliminarCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.EliminarCliente(id));
    }

    @DeleteMapping("/{id}/fisico")
    public ResponseEntity<String> EliminarClienteFisico(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.EliminarClienteFisico(id));
    }
}