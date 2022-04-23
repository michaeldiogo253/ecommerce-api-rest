package estudos.ecommerce.itemdocarrinho.application.port.in;

import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;

public interface RemoveItemDoCarrinhoPorQuantidadeUseCase {

    ItemDoCarrinho execute(Carrinho carrinho, Long idProduto, Integer quantidade);
}
