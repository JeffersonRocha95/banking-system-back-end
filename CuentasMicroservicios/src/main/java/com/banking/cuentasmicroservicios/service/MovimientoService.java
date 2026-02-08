package com.banking.cuentasmicroservicios.service;

import com.banking.cuentasmicroservicios.dto.MovimientoDto;
import com.banking.cuentasmicroservicios.entity.Cuenta;
import com.banking.cuentasmicroservicios.entity.Movimiento;
import com.banking.cuentasmicroservicios.exception.RecursoNoEncontradoException;
import com.banking.cuentasmicroservicios.exception.SaldoNoDisponibleException;
import com.banking.cuentasmicroservicios.mapper.MovimientoMapper;
import com.banking.cuentasmicroservicios.repository.CuentaRepository;
import com.banking.cuentasmicroservicios.repository.MovimientoRepository;
import com.banking.cuentasmicroservicios.service.interfaces.IMovimientoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoService implements IMovimientoService{
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository,
                             CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public MovimientoDto RegistrarMovimiento(MovimientoDto movimientoDto) {

        Cuenta cuenta = ObtenerCuenta(movimientoDto.getCuentaId());

        double saldoActual = cuenta.getSaldoInicial();
        double valorMovimiento = movimientoDto.getValor();

        double nuevoSaldo = CalcularNuevoSaldo(
                saldoActual,
                valorMovimiento,
                movimientoDto.getTipoMovimiento()
        );

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimientoDto.setSaldo(nuevoSaldo);

        Movimiento movimiento = MovimientoMapper.MapearDtoAEntidad(movimientoDto, cuenta);
        Movimiento guardado = movimientoRepository.save(movimiento);

        return MovimientoMapper.MapearEntidadADto(guardado);
    }

    private double CalcularNuevoSaldo(
            double saldoActual,
            double valorMovimiento,
            String tipoMovimiento
    ) {

        if ("RETIRO".equalsIgnoreCase(tipoMovimiento)) {
            if (saldoActual < valorMovimiento) {
                throw new SaldoNoDisponibleException("Saldo no disponible");
            }
            return saldoActual - valorMovimiento;
        }

        if ("DEPOSITO".equalsIgnoreCase(tipoMovimiento)) {
            return saldoActual + valorMovimiento;
        }

        throw new IllegalArgumentException("Tipo de movimiento inválido");
    }

    private Cuenta ObtenerCuenta(Long cuentaId) {
        return cuentaRepository.findById(cuentaId)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cuenta no encontrada con id: " + cuentaId
                        )
                );
    }

    @Override
    public List<MovimientoDto> ListarMovimientosPorCuenta(Long cuentaId) {

        return movimientoRepository.findByCuentaId(cuentaId)
                .stream()
                .map(MovimientoMapper::MapearEntidadADto)
                .collect(Collectors.toList());
    }
}
