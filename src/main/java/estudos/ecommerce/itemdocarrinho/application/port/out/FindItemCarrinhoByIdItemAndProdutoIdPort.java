package estudos.ecommerce.itemdocarrinho.application.port.out;

import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;

import java.util.Optional;

public interface FindItemCarrinhoByIdItemAndProdutoIdPort {

    Optional<ItemDoCarrinho> findItemCarrinhoByIdItemAndProdutoId(Long idItemCarrinho, Long idProduto);
}
