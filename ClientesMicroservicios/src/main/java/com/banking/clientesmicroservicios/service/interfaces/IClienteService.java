package com.banking.clientesmicroservicios.service.interfaces;

import com.banking.clientesmicroservicios.dto.ClienteDto;
import java.util.List;

public interface IClienteService {
    ClienteDto CrearCliente(ClienteDto clienteDto);
    ClienteDto ObtenerClientePorId(Long id);
    List<ClienteDto> ListarClientes();
    ClienteDto ActualizarCliente(Long id, ClienteDto clienteDto);
    String EliminarCliente(Long id);
    String EliminarClienteFisico(Long id);
}