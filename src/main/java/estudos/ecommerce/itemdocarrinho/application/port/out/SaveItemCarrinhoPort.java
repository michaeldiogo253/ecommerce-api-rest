package estudos.ecommerce.itemdocarrinho.application.port.out;

import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;

public interface SaveItemCarrinhoPort {

    ItemDoCarrinho salvaItemNoCarrinho(ItemDoCarrinho itemDoCarrinho);
}
