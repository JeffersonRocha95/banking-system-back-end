package com.banking.cuentasmicroservicios.service.interfaces;

import com.banking.cuentasmicroservicios.dto.CuentaDto;
import java.util.List;

public interface ICuentaService {
    CuentaDto CrearCuenta(CuentaDto cuentaDTO);
    CuentaDto ObtenerCuentaPorId(Long id);
    List<CuentaDto> ListarCuentasPorCliente(Long clienteId);
    CuentaDto ActualizarCuenta(Long id, CuentaDto cuentaDto);
    String EliminarCuenta(Long id);
    String EliminarCuentaFisico(Long id);
}