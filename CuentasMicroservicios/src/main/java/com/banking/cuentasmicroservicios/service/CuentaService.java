package com.banking.cuentasmicroservicios.service;

import com.banking.cuentasmicroservicios.dto.CuentaDto;
import com.banking.cuentasmicroservicios.entity.Cuenta;
import com.banking.cuentasmicroservicios.entity.Movimiento;
import com.banking.cuentasmicroservicios.exception.RecursoNoEncontradoException;
import com.banking.cuentasmicroservicios.mapper.CuentaMapper;
import com.banking.cuentasmicroservicios.repository.CuentaRepository;
import com.banking.cuentasmicroservicios.repository.MovimientoRepository;
import com.banking.cuentasmicroservicios.service.interfaces.ICuentaService;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService implements ICuentaService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public CuentaService(CuentaRepository cuentaRepository,
                         MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    @Override
    public CuentaDto CrearCuenta(CuentaDto cuentaDto) {

        Cuenta cuenta = CuentaMapper.MapearDtoAEntidad(cuentaDto);
        cuenta.setEstado(true);

        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(LocalDate.now());
        movimiento.setTipoMovimiento("DEPOSITO");
        movimiento.setValor(cuentaGuardada.getSaldoInicial());
        movimiento.setSaldo(cuentaGuardada.getSaldoInicial());
        movimiento.setCuenta(cuentaGuardada);

        movimientoRepository.save(movimiento);


        return CuentaMapper.MapearEntidadADto(cuentaGuardada);
    }

    @Override
    public CuentaDto ObtenerCuentaPorId(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cuenta no encontrada con id: " + id
                        )
                );

        return CuentaMapper.MapearEntidadADto(cuenta);
    }

    @Override
    public List<CuentaDto> ListarCuentasPorCliente(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId)
                .stream()
                .map(CuentaMapper::MapearEntidadADto)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDto ActualizarCuenta(Long id, CuentaDto cuentaDTO) {

        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cuenta no encontrada con id: " + id
                        )
                );

        CuentaMapper.MapearActualizarEntidad(cuenta, cuentaDTO, false);

        Cuenta actualizada = cuentaRepository.save(cuenta);
        return CuentaMapper.MapearEntidadADto(actualizada);
    }

    @Override
    public String EliminarCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cuenta no encontrada con id: " + id
                        )
                );

        if (!cuenta.getEstado()) {
            return "La cuenta ya se encuentra inactiva";
        }

        cuenta.setEstado(false);
        cuentaRepository.save(cuenta);

        return "Cuenta eliminada lógicamente de forma correcta";
    }

    @Override
    public String EliminarCuentaFisico(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cuenta no encontrada con id: " + id
                        )
                );

        cuentaRepository.delete(cuenta);
        return "Cuenta eliminada físicamente de la base de datos";
    }
}
