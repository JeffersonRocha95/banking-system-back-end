package com.banking.cuentasmicroservicios.mapper;

import com.banking.cuentasmicroservicios.dto.MovimientoDto;
import com.banking.cuentasmicroservicios.entity.Cuenta;
import com.banking.cuentasmicroservicios.entity.Movimiento;
import java.time.LocalDate;

public class MovimientoMapper {

    private MovimientoMapper() {
    }

    public static Movimiento MapearDtoAEntidad(MovimientoDto dto, Cuenta cuenta) {
        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setValor(dto.getValor());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setFecha(LocalDate.now());
        movimiento.setSaldo(dto.getSaldo());
        return movimiento;
    }

    public static MovimientoDto MapearEntidadADto(Movimiento movimiento) {
        MovimientoDto dto = new MovimientoDto();
        dto.setId(movimiento.getId());
        dto.setValor(movimiento.getValor());
        dto.setSaldo(movimiento.getSaldo());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());
        dto.setFecha(movimiento.getFecha());

        if (movimiento.getCuenta() != null) {
            dto.setCuentaId(movimiento.getCuenta().getId());
        }
        return dto;
    }
}
