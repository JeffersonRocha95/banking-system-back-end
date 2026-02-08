package com.banking.cuentasmicroservicios.dto.report;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoReporteDto {
    private LocalDate fecha;
    private String tipoMovimiento;
    private double movimiento;
    private double saldoDisponible;
}
