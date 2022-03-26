package estudos.ecommerce.carrinho.application.port.out;

import estudos.ecommerce.carrinho.domain.Carrinho;

public interface SaveCarrinhoPort {

    Carrinho salvarCarrinho(Carrinho carrinho);
}
