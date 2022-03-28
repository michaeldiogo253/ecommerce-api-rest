package estudos.ecommerce.carrinho.application.service;

import estudos.ecommerce.carrinho.application.port.in.RemoveProdutoDoCarrinhoUseCase;
import estudos.ecommerce.carrinho.application.port.out.FindCarrinhoByIdClientePort;
import estudos.ecommerce.carrinho.application.port.out.SaveCarrinhoPort;
import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.application.port.in.RemoveItemDoCarrinhoUseCase;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class RemoveProdutoDoCarrinhoService implements RemoveProdutoDoCarrinhoUseCase {

    private final FindCarrinhoByIdClientePort findCarrinhoByIdClientePort;
    private final RemoveItemDoCarrinhoUseCase removeItemDoCarrinhoUseCase;
    private final SaveCarrinhoPort saveCarrinhoPort;

    @Override
    public void execute(Long idCliente, Long idProduto, Integer quantidade) {

        Carrinho carrinho = findCarrinhoByIdClientePort.findCarrinhoByIdCliente(idCliente)
                                                                     .orElseThrow(() -> new IllegalArgumentException(
                                                                             "Carrinho do cliente não encontrado"));

        ItemDoCarrinho itemDoCarrinho = removeItemDoCarrinhoUseCase.execute(carrinho, idProduto, quantidade);

        if (verificaSePrecisaDeletarItemCarrinho(itemDoCarrinho)) {
            carrinho.removeItemDoCarrinho(itemDoCarrinho);
        }
        carrinho.atualizaTotaisDoCarrinho();
        saveCarrinhoPort.salvarCarrinho(carrinho);

    }

    public boolean verificaSePrecisaDeletarItemCarrinho(ItemDoCarrinho itemDoCarrinho) {

        return itemDoCarrinho.getQuantidade() == 0;
    }
}
