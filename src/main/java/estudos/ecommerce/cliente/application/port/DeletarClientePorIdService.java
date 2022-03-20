package estudos.ecommerce.cliente.application.port;

import estudos.ecommerce.cliente.application.port.in.DeletarClientePorIdUseCase;
import estudos.ecommerce.cliente.application.port.out.DeleteClienteByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeletarClientePorIdService implements DeletarClientePorIdUseCase {

    private final DeleteClienteByIdPort deleteClienteByIdPort;

    @Override
    public void execute(Long idCliente) {

        deleteClienteByIdPort.deletarClientePorId(idCliente);
    }
}
