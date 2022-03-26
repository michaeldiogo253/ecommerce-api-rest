package estudos.ecommerce.itemdocarrinho.application.port.in;

import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;

public interface CriaNovoItemCarrinhoUseCase {

    ItemDoCarrinho criaNovoItemDoCarrinho(Long idProduto, Integer quantidade, Carrinho carrinho);
}
