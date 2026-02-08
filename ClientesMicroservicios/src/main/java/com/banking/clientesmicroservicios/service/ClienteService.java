package com.banking.clientesmicroservicios.service;

import com.banking.clientesmicroservicios.entity.Cliente;
import com.banking.clientesmicroservicios.exception.RecursoNoEncontradoException;
import com.banking.clientesmicroservicios.mapper.ClienteMapper;
import com.banking.clientesmicroservicios.dto.ClienteDto;
import com.banking.clientesmicroservicios.messaging.ClienteEventPublisher;
import com.banking.clientesmicroservicios.repository.ClienteRepository;
import com.banking.clientesmicroservicios.service.interfaces.IClienteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService  implements IClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteEventPublisher clienteEventPublisher;

    public ClienteService(ClienteRepository clienteRepository,ClienteEventPublisher clienteEventPublisher) {
        this.clienteRepository = clienteRepository;
        this.clienteEventPublisher =clienteEventPublisher;
    }

    @Transactional
    public ClienteDto CrearCliente(ClienteDto dto) {
        Cliente cliente = ClienteMapper.MapearDtoAEntidad(dto);
        cliente.setEstado(true);

        Cliente guardado = clienteRepository.save(cliente);
        try {
            clienteEventPublisher.publicarClienteCreado(guardado);
        } catch (Exception e) {
            throw new RecursoNoEncontradoException(
                    "problemas al conectarse con Rabbit");
        }

        return ClienteMapper.MapearEntidadADto(guardado);
    }

    @Override
    public ClienteDto ObtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado con id: " + id
                        )
                );

        return ClienteMapper.MapearEntidadADto(cliente);
    }

    @Override
    public List<ClienteDto> ListarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::MapearEntidadADto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto ActualizarCliente(Long id, ClienteDto dto) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado con id: " + id
                        )
                );

        ClienteMapper.MapearActualizarEntidad(cliente, dto);

        Cliente actualizado = clienteRepository.save(cliente);
        return ClienteMapper.MapearEntidadADto(actualizado);
    }

    @Override
    public String EliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado con id: " + id
                        )
                );

        if (!cliente.getEstado()) {
            return "El cliente ya se encuentra inactivo";
        }

        cliente.setEstado(false);
        clienteRepository.save(cliente);

        return "Cliente eliminado lógicamente de forma correcta";
    }

    @Override
    public String EliminarClienteFisico(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado con id: " + id
                        )
                );

        clienteRepository.delete(cliente);

        return "Cliente eliminado físicamente de la base de datos";
    }
}
