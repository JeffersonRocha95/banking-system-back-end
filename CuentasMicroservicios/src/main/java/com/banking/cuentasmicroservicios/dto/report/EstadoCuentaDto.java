package com.banking.cuentasmicroservicios.dto.report;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoCuentaDto {
    private Long clienteId;
    private String cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<CuentaReporteDto> cuentas;
}
