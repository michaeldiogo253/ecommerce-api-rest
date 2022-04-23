package estudos.ecommerce.itemdocarrinho.application.service;

import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.application.port.in.RemoveItemDoCarrinhoPorQuantidadeUseCase;
import estudos.ecommerce.itemdocarrinho.application.port.out.FindItemCarrinhoByIdCarrinhoAndIdProdutoPort;
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
public class RemoveItemDoCarrinhoPorQuantidadeService implements RemoveItemDoCarrinhoPorQuantidadeUseCase {

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
            throw new BussinessRuleException("A quantidade de itens do carrinho é menor que a quantidade informada para remoção");
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
