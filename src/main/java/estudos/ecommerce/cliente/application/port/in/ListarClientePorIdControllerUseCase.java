package estudos.ecommerce.cliente.application.port.in;

import estudos.ecommerce.cliente.domain.Cliente;

public interface ListarClientePorIdControllerUseCase {

    Cliente execute(Long idCliente);
}
