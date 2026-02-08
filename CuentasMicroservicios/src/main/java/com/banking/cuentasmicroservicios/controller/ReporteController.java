package com.banking.cuentasmicroservicios.controller;

import com.banking.cuentasmicroservicios.dto.report.EstadoCuentaDto;
import com.banking.cuentasmicroservicios.service.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<EstadoCuentaDto> GenerarEstadoCuenta(
            @RequestParam Long clienteId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaInicio,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaFin
    ) {

        EstadoCuentaDto reporte = reporteService.GenerarEstadoCuenta(
                clienteId,
                fechaInicio,
                fechaFin
        );

        return ResponseEntity.ok(reporte);
    }
}
