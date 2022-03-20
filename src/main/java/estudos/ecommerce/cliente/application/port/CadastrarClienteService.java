package estudos.ecommerce.cliente.application.port;

import estudos.ecommerce.cliente.application.port.in.CadastrarClienteUseCase;
import estudos.ecommerce.cliente.application.port.out.SalvarClientePort;
import estudos.ecommerce.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CadastrarClienteService implements CadastrarClienteUseCase {

    private final SalvarClientePort salvarClientePort;

    @Override
    public Cliente execute(Cliente cliente) {

        Cliente clienteSalvo = salvarClientePort.salvarCliente(cliente);
        return clienteSalvo;
    }
}