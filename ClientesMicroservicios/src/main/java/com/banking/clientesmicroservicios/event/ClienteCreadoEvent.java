package com.banking.clientesmicroservicios.event;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCreadoEvent implements Serializable {

    private Long id;
    private String nombre;
    private Boolean estado;

}