package com.banking.cuentasmicroservicios.repository;

import com.banking.cuentasmicroservicios.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByClienteId(Long clienteId);
}
