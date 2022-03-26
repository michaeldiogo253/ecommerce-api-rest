package estudos.ecommerce.carrinho.application.port.in;

import estudos.ecommerce.carrinho.domain.Carrinho;

public interface AdicionaProdutoNoCarrinhoUseCase {

    Carrinho execute(Long idCliente, Long idProduto, Integer quantidade);

}
