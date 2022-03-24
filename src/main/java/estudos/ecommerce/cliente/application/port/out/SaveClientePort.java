package estudos.ecommerce.cliente.application.port.out;

import estudos.ecommerce.cliente.domain.Cliente;

public interface SaveClientePort {

    Cliente salvarCliente(Cliente cliente);
}
