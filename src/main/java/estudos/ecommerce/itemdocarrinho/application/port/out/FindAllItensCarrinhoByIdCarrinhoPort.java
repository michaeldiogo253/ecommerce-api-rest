package estudos.ecommerce.itemdocarrinho.application.port.out;

import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import java.util.List;

public interface FindAllItensCarrinhoByIdCarrinhoPort {

    List<ItemDoCarrinho> findItensDoCarrinhoByIdCarrinho(Long idCarrinho);
}
