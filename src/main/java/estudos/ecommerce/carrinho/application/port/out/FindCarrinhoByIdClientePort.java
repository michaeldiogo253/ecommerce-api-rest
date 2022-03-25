package estudos.ecommerce.carrinho.application.port.out;

import estudos.ecommerce.carrinho.domain.Carrinho;

import java.util.Optional;

public interface FindCarrinhoByIdClientePort {

    Optional<Carrinho> findCarrinhoByIdCliente(Long idCliente);
}
