package estudos.ecommerce.carrinho.application.port.in;

import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;

public interface AdicionaProdutoEmUmCarrinhoExistenteUseCase {

    Carrinho adicionaProdutoEmCarrinhoExistente(Carrinho carrinhoBuscado,
                                                ItemDoCarrinho itemDoCarrinho,
                                                Integer quantidade);

}
