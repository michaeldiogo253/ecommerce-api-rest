package estudos.ecommerce.cliente.application.port.out;

import estudos.ecommerce.cliente.domain.Cliente;

import java.util.Optional;

public interface FindOptionalClienteByIdPort {

    Optional<Cliente> findClienteOptionalById(Long idCliente);
}
