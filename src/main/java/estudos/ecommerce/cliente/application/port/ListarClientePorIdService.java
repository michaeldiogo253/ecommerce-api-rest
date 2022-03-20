package estudos.ecommerce.cliente.application.port;

import estudos.ecommerce.cliente.application.port.in.ListarClientePorIdControllerUseCase;
import estudos.ecommerce.cliente.application.port.out.FindClienteByIdPort;
import estudos.ecommerce.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ListarClientePorIdService implements ListarClientePorIdControllerUseCase {

    private final FindClienteByIdPort  findClienteByIdPort;

    @Override
    public Cliente execute(Long idCliente) {

        return findClienteByIdPort.buscaClientePorId(idCliente);
    }
}
