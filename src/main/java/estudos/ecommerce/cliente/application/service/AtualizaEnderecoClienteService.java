package estudos.ecommerce.cliente.application.service;

import estudos.ecommerce.cliente.application.port.out.FindClienteByIdPort;
import estudos.ecommerce.cliente.application.port.out.SaveClientePort;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.cliente.domain.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AtualizaEnderecoClienteService {

    private final FindClienteByIdPort findClienteByIdPort;
    private final SaveClientePort saveClientePort;

    public void atualizaEndereco(Endereco novoEndereco, Long idCliente){

        Cliente cliente = findClienteByIdPort.buscaClientePorId(idCliente);

        cliente.atualizaEndereco(novoEndereco);

        saveClientePort.salvarCliente(cliente);

    }

}
