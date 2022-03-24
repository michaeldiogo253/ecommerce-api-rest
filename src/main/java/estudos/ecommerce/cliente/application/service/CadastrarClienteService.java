package estudos.ecommerce.cliente.application.service;

import estudos.ecommerce.cliente.application.port.in.CadastrarClienteUseCase;
import estudos.ecommerce.cliente.application.port.out.SaveClientePort;
import estudos.ecommerce.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CadastrarClienteService implements CadastrarClienteUseCase {

    private final SaveClientePort salvarClientePort;

    @Override
    public Cliente execute(Cliente cliente) {

        Cliente clienteSalvo = salvarClientePort.salvarCliente(cliente);
        return clienteSalvo;
    }
}