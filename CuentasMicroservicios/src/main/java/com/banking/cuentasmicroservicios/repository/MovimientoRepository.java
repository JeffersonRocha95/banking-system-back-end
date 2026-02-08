package com.banking.cuentasmicroservicios.repository;

import com.banking.cuentasmicroservicios.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaId(Long cuentaId);

    @Query("""
                SELECT m
                FROM Movimiento m
                JOIN m.cuenta c
                WHERE c.clienteId = :clienteId
                  AND m.fecha BETWEEN :fechaInicio AND :fechaFin
                ORDER BY m.fecha
            """)
    List<Movimiento> ObtenerEstadoCuenta(
            @Param("clienteId") Long clienteId,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

}