package estudos.ecommerce.cliente.application.port.out;

import estudos.ecommerce.cliente.domain.Cliente;

import java.util.List;

public interface FindAllClientesPort {

    List<Cliente> listarTodosOsClientes();
}
