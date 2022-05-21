package estudos.ecommerce.cliente.adapter.out.persistence;

import estudos.ecommerce.cliente.application.port.out.*;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientePersistenceAdapter
        implements SaveClientePort,
                   FindAllClientesPort,
                   FindClienteByIdPort,
                   DeleteClienteByIdPort,
                   FindOptionalClienteByIdPort {

    private final ClienteRepository clienteRepository;

    @Override
    public void deletarClientePorId(Long idCliente) {

        if(clienteRepository.findById(idCliente).isPresent()){
            clienteRepository.deleteById(idCliente);
            return;
        }
        throw new ResourceNotFoundException("Não foi encontrado cliente com este ID");
    }

    @Override
    public List<Cliente> listarTodosOsClientes() {

        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscaClientePorId(Long idCliente) {

        return clienteRepository.findById(idCliente)
                                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {

        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> findClienteOptionalById(Long idCliente) {

        return clienteRepository.findById(idCliente);
    }
}
