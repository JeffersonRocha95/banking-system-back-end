package com.banking.cuentasmicroservicios.service;

import com.banking.cuentasmicroservicios.client.ClienteClient;
import com.banking.cuentasmicroservicios.dto.ClienteDto;
import com.banking.cuentasmicroservicios.dto.report.CuentaReporteDto;
import com.banking.cuentasmicroservicios.dto.report.EstadoCuentaDto;
import com.banking.cuentasmicroservicios.dto.report.MovimientoReporteDto;
import com.banking.cuentasmicroservicios.entity.Cuenta;
import com.banking.cuentasmicroservicios.entity.Movimiento;
import com.banking.cuentasmicroservicios.repository.MovimientoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReporteService {

    private final MovimientoRepository movimientoRepository;
    private final ClienteClient clienteClient;

    public ReporteService(MovimientoRepository movimientoRepository,
                          ClienteClient clienteClient) {
        this.movimientoRepository = movimientoRepository;
        this.clienteClient = clienteClient;
    }

    public EstadoCuentaDto GenerarEstadoCuenta(
            Long clienteId,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) {

        List<Movimiento> movimientos =
                movimientoRepository.ObtenerEstadoCuenta(
                        clienteId, fechaInicio, fechaFin
                );

        Map<Cuenta, List<Movimiento>> movimientosPorCuenta =
                movimientos.stream()
                        .collect(Collectors.groupingBy(Movimiento::getCuenta));

        EstadoCuentaDto reporte = new EstadoCuentaDto();
        reporte.setClienteId(clienteId);
        reporte.setFechaInicio(fechaInicio);
        reporte.setFechaFin(fechaFin);

        // 🔥 AQUÍ SE OBTIENE EL NOMBRE DEL CLIENTE
        ClienteDto cliente = clienteClient.obtenerCliente(clienteId);
        reporte.setCliente(cliente.getNombre());

        List<CuentaReporteDto> cuentasDto = new ArrayList<>();

        for (Map.Entry<Cuenta, List<Movimiento>> entry : movimientosPorCuenta.entrySet()) {

            Cuenta cuenta = entry.getKey();

            CuentaReporteDto cuentaDto = new CuentaReporteDto();
            cuentaDto.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaDto.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaDto.setSaldoInicial(cuenta.getSaldoInicial());
            cuentaDto.setEstado(cuenta.getEstado());

            List<MovimientoReporteDto> movimientosDto =
                    entry.getValue().stream()
                            .map(this::mapearMovimiento)
                            .toList();

            cuentaDto.setMovimientos(movimientosDto);
            cuentasDto.add(cuentaDto);
        }

        reporte.setCuentas(cuentasDto);
        return reporte;
    }

    private MovimientoReporteDto mapearMovimiento(Movimiento movimiento) {

        MovimientoReporteDto dto = new MovimientoReporteDto();
        dto.setFecha(movimiento.getFecha());
        dto.setTipoMovimiento(movimiento.getTipoMovimiento());

        double valor = movimiento.getValor();
        if ("RETIRO".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            valor = -valor;
        }

        dto.setMovimiento(valor);
        dto.setSaldoDisponible(movimiento.getSaldo());

        return dto;
    }
}
