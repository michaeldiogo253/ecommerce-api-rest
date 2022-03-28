package estudos.ecommerce.itemdocarrinho.application.port.out;

import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;

import java.util.Optional;

public interface FindItemCarrinhoByIdCarrinhoAndIdProdutoPort {

    Optional<ItemDoCarrinho> findItemCarrinhoByIdCarrinhoAndIdProduto(Long idCarrinho, Long idProduto);
}
