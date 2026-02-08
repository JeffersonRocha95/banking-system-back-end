package com.banking.clientesmicroservicios.mapper;

import com.banking.clientesmicroservicios.dto.ClienteDto;
import com.banking.clientesmicroservicios.entity.Cliente;

public class ClienteMapper {
    private ClienteMapper() {}

    public static Cliente MapearDtoAEntidad(ClienteDto dto) {
        Cliente cliente = new Cliente();
        MapearActualizarEntidad(cliente, dto);
        return cliente;
    }

    public static void MapearActualizarEntidad(Cliente cliente, ClienteDto dto) {
        CopiarDtoAEntidad(dto, cliente);
    }

    public static ClienteDto MapearEntidadADto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        CopiarEntidadADto(cliente, dto);
        return dto;
    }

    private static void CopiarDtoAEntidad(ClienteDto dto, Cliente cliente) {
        cliente.setNombre(dto.getNombre());
        cliente.setGenero(dto.getGenero());
        cliente.setEdad(dto.getEdad());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setClienteId(dto.getClienteId());
        cliente.setContrasenia(dto.getContrasenia());
    }

    private static void CopiarEntidadADto(Cliente cliente, ClienteDto dto) {
        dto.setNombre(cliente.getNombre());
        dto.setGenero(cliente.getGenero());
        dto.setEdad(cliente.getEdad());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        dto.setClienteId(cliente.getClienteId());
        dto.setContrasenia(cliente.getContrasenia());
    }
}
