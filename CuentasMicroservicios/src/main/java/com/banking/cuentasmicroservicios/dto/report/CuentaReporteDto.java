package com.banking.cuentasmicroservicios.dto.report;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaReporteDto {
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private boolean estado;
    private List<MovimientoReporteDto> movimientos;
}
