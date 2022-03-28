package estudos.ecommerce.itemdocarrinho.adapter.out.persistence;

import estudos.ecommerce.itemdocarrinho.application.port.out.FindItemCarrinhoByIdCarrinhoAndIdProdutoPort;
import estudos.ecommerce.itemdocarrinho.application.port.out.FindItemCarrinhoByIdItemAndProdutoIdPort;
import estudos.ecommerce.itemdocarrinho.application.port.out.SaveItemCarrinhoPort;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemDoCarrinhoPersistenceAdapter implements FindItemCarrinhoByIdItemAndProdutoIdPort,
                                                         SaveItemCarrinhoPort ,
                                                         FindItemCarrinhoByIdCarrinhoAndIdProdutoPort {

    private final ItemDoCarrinhoRepository itemDoCarrinhoRepository;

    @Override
    public Optional<ItemDoCarrinho> findItemCarrinhoByIdItemAndProdutoId(Long idItemCarrinho, Long idProduto) {

        return itemDoCarrinhoRepository.findItemCarrinhoByIdCarrinhoAndIdProduto(idItemCarrinho, idProduto);

    }

    @Override
    public ItemDoCarrinho salvaItemNoCarrinho(ItemDoCarrinho itemDoCarrinho) {

       return  itemDoCarrinhoRepository.save(itemDoCarrinho);
    }

    @Override
    public Optional<ItemDoCarrinho> findItemCarrinhoByIdCarrinhoAndIdProduto(Long idCarrinho, Long idProduto) {

        return itemDoCarrinhoRepository.findItemCarrinhoByIdCarrinhoAndIdProduto(idCarrinho, idProduto);
    }
}
