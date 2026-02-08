package com.banking.cuentasmicroservicios.mapper;

import com.banking.cuentasmicroservicios.dto.CuentaDto;
import com.banking.cuentasmicroservicios.entity.Cuenta;

public class CuentaMapper {

    private CuentaMapper() {
    }

    public static Cuenta MapearDtoAEntidad(CuentaDto dto) {
        Cuenta cuenta = new Cuenta();
        MapearActualizarEntidad(cuenta, dto, true);
        cuenta.setClienteId(dto.getClienteId());
        return cuenta;
    }

    public static void MapearActualizarEntidad(Cuenta cuenta, CuentaDto dto, boolean esCreacion) {

        cuenta.setEstado(dto.getEstado());

        if (esCreacion) {
            cuenta.setSaldoInicial(dto.getSaldoInicial());
            cuenta.setNumeroCuenta(dto.getNumeroCuenta());
            cuenta.setTipoCuenta(dto.getTipoCuenta());
            cuenta.setClienteId(dto.getClienteId());
        }
    }

    public static CuentaDto MapearEntidadADto(Cuenta cuenta) {
        CuentaDto dto = new CuentaDto();

        dto.setId(cuenta.getId());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta());
        dto.setSaldoInicial(cuenta.getSaldoInicial());
        dto.setEstado(cuenta.getEstado());
        dto.setClienteId(cuenta.getClienteId());

        return dto;
    }
}
