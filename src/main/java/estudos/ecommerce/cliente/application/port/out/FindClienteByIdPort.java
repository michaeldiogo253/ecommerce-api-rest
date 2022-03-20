package estudos.ecommerce.cliente.application.port.out;

import estudos.ecommerce.cliente.domain.Cliente;

public interface FindClienteByIdPort {

    Cliente buscaClientePorId(Long idCliente);
}
