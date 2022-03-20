package estudos.ecommerce.cliente.application.port;

import estudos.ecommerce.cliente.application.port.in.AtualizaDadosPessoaisClienteUseCase;
import estudos.ecommerce.cliente.application.port.out.FindClienteByIdPort;
import estudos.ecommerce.cliente.application.port.out.SalvarClientePort;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.cliente.domain.DadosPessoais;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtualizaDadosPessoaisClienteService implements AtualizaDadosPessoaisClienteUseCase {

    private final FindClienteByIdPort findClienteByIdPort;
    private final SalvarClientePort salvarClientePort;

    @Override
    public Cliente execute(Long idCliente, DadosPessoais dadosPessoais) {

        Cliente cliente = findClienteByIdPort.buscaClientePorId(idCliente);
        cliente.atualizaDadosPessoais(dadosPessoais);
        salvarClientePort.salvarCliente(cliente);
        return cliente;
    }
}
