package estudos.ecommerce.cliente.application.port.in;

import estudos.ecommerce.cliente.domain.Cliente;

public interface CadastrarClienteUseCase {

    Cliente execute(Cliente cliente);
}
