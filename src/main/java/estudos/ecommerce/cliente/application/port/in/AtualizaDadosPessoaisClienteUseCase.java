package estudos.ecommerce.cliente.application.port.in;

import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.cliente.domain.DadosPessoais;

public interface AtualizaDadosPessoaisClienteUseCase {

    Cliente execute(Long idCliente, DadosPessoais dadosPessoais);
}
