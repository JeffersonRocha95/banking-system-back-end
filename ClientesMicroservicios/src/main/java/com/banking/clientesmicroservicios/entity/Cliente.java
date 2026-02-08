package com.banking.clientesmicroservicios.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
@NoArgsConstructor
public class Cliente extends Persona
{
    @Column(nullable = false, unique = true)
    private String clienteId;

    @Column(nullable = false)
    private String contrasenia;

    @Column(nullable = false)
    private Boolean estado;
}

