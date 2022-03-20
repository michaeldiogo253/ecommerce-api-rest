package estudos.ecommerce.cliente.application.port.in;

import estudos.ecommerce.cliente.domain.Cliente;

import java.util.List;

public interface ListarTodosOsClientesUseCase {

    List<Cliente> execute();
}
