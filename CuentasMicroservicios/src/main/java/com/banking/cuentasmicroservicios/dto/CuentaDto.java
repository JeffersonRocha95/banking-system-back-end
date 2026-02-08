package com.banking.cuentasmicroservicios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDto {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private Long clienteId;
}
