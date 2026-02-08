package com.banking.cuentasmicroservicios.service.interfaces;

import com.banking.cuentasmicroservicios.dto.MovimientoDto;
import java.util.List;

public interface IMovimientoService {

    MovimientoDto RegistrarMovimiento(MovimientoDto movimientoDto);
    List<MovimientoDto> ListarMovimientosPorCuenta(Long cuentaId);
}
