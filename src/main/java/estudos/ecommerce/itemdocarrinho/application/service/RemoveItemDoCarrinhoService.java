package estudos.ecommerce.itemdocarrinho.application.service;

import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.application.port.in.RemoveItemDoCarrinhoUseCase;
import estudos.ecommerce.itemdocarrinho.application.port.out.FindItemCarrinhoByIdCarrinhoAndIdProdutoPort;
import estudos.ecommerce.itemdocarrinho.application.port.out.FindItemCarrinhoByIdItemAndProdutoIdPort;
import estudos.ecommerce.itemdocarrinho.application.port.out.SaveItemCarrinhoPort;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import estudos.ecommerce.util.exception.BussinessRuleException;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class RemoveItemDoCarrinhoService implements RemoveItemDoCarrinhoUseCase {

    private final FindItemCarrinhoByIdCarrinhoAndIdProdutoPort findItemCarrinhoByIdCarrinhoAndIdProdutoPort;
    private final SaveItemCarrinhoPort saveItemCarrinhoPort;

    @Override
    public ItemDoCarrinho execute(Carrinho carrinho, Long idProduto, Integer quantidade) {

        ItemDoCarrinho itemDoCarrinho =
                findItemCarrinhoByIdCarrinhoAndIdProdutoPort
                        .findItemCarrinhoByIdCarrinhoAndIdProduto(carrinho.getId(),
                                                                  idProduto)
                        .orElseThrow(() -> new ResourceNotFoundException("Produto não existe no carrinho"));

        if (!verificaSePodeSubtrairQuantidadeDoProduto(itemDoCarrinho, quantidade)) {
            throw new BussinessRuleException("A quantidade informada para remoção é maior do que a quantidade de produtos que o carrinho " +
                    "possui");
        }

        itemDoCarrinho.subtraiQuantidade(quantidade);
        saveItemCarrinhoPort.salvaItemNoCarrinho(itemDoCarrinho);

        return itemDoCarrinho;
    }

    public boolean verificaSePodeSubtrairQuantidadeDoProduto(ItemDoCarrinho itemDoCarrinhoBuscado,
                                                             Integer quantidadeASerSubtraida) {
        return itemDoCarrinhoBuscado.getQuantidade() >= quantidadeASerSubtraida;
    }
}
