package estudos.ecommerce.cliente.application.port.out;

import estudos.ecommerce.cliente.domain.Cliente;

public interface SalvarClientePort {

    Cliente salvarCliente(Cliente cliente);
}
