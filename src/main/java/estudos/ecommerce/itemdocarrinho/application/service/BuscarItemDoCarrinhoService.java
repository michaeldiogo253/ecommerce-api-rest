package estudos.ecommerce.itemdocarrinho.application.service;

import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.application.port.in.BuscaItemDoCarrinhoUseCase;
import estudos.ecommerce.itemdocarrinho.application.port.in.CriaNovoItemCarrinhoUseCase;
import estudos.ecommerce.itemdocarrinho.application.port.out.FindItemCarrinhoByIdItemAndProdutoIdPort;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BuscarItemDoCarrinhoService implements BuscaItemDoCarrinhoUseCase {

    private final FindItemCarrinhoByIdItemAndProdutoIdPort findItemCarrinhoByIdItemAndProdutoIdPort;
    private final CriaNovoItemCarrinhoUseCase criaNovoItemCarrinhoUseCase;

    @Override
    public ItemDoCarrinho execute(Carrinho carrinho, Long produtoId, Integer quantidade) {

        Optional<ItemDoCarrinho> itemCarrinho =
                findItemCarrinhoByIdItemAndProdutoIdPort.findItemCarrinhoByIdItemAndProdutoId(
                        carrinho.getId(),
                        produtoId);

        return itemCarrinho.orElseGet(() -> criaNovoItemCarrinhoUseCase.criaNovoItemDoCarrinho(produtoId,
                                                                                               quantidade,
                                                                                               carrinho));

    }
}
