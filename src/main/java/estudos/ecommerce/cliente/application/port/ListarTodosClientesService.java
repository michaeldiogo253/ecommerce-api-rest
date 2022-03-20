package estudos.ecommerce.cliente.application.port;

import estudos.ecommerce.cliente.application.port.in.ListarTodosOsClientesUseCase;
import estudos.ecommerce.cliente.application.port.out.FindAllClientesPort;
import estudos.ecommerce.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ListarTodosClientesService implements ListarTodosOsClientesUseCase {

    private final FindAllClientesPort findAllClientesPort;
    @Override
    public List<Cliente> execute() {

        return findAllClientesPort.listarTodosOsClientes();
    }
}
