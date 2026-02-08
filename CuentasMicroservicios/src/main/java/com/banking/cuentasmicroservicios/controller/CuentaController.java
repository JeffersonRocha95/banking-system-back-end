package com.banking.cuentasmicroservicios.controller;

import com.banking.cuentasmicroservicios.dto.CuentaDto;
import com.banking.cuentasmicroservicios.service.interfaces.ICuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final ICuentaService cuentaService;

    public CuentaController(ICuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<CuentaDto> CrearCuenta(@RequestBody CuentaDto dto) {
        CuentaDto creada = cuentaService.CrearCuenta(dto);
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDto> ObtenerCuentaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.ObtenerCuentaPorId(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CuentaDto>> ListarCuentasPorCliente(
            @PathVariable Long clienteId
    ) {
        return ResponseEntity.ok(
                cuentaService.ListarCuentasPorCliente(clienteId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDto> ActualizarCuenta(
            @PathVariable Long id,
            @RequestBody CuentaDto dto
    ) {
        return ResponseEntity.ok(
                cuentaService.ActualizarCuenta(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> EliminarCuenta(@PathVariable Long id) {
        return ResponseEntity.ok(
                cuentaService.EliminarCuenta(id)
        );
    }

    @DeleteMapping("/{id}/fisico")
    public ResponseEntity<String> EliminarCuentaFisico(@PathVariable Long id) {
        return ResponseEntity.ok(
                cuentaService.EliminarCuentaFisico(id)
        );
    }

}
