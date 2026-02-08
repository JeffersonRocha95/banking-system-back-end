package com.banking.cuentasmicroservicios.controller;

import com.banking.cuentasmicroservicios.dto.MovimientoDto;
import com.banking.cuentasmicroservicios.service.interfaces.IMovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final IMovimientoService movimientoService;

    public MovimientoController(IMovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<MovimientoDto> RegistrarMovimiento(
            @RequestBody MovimientoDto movimientoDto
    ) {
        MovimientoDto registrado =
                movimientoService.RegistrarMovimiento(movimientoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrado);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<MovimientoDto>> ListarMovimientosPorCuenta(
            @PathVariable Long cuentaId
    ) {
        return ResponseEntity.ok(
                movimientoService.ListarMovimientosPorCuenta(cuentaId)
        );
    }
}
