package estudos.ecommerce.itemdocarrinho.application.service;

import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.application.port.in.CriaNovoItemCarrinhoUseCase;
import estudos.ecommerce.itemdocarrinho.application.port.out.SaveItemCarrinhoPort;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import estudos.ecommerce.produto.application.port.out.FindProdutoByIdPort;
import estudos.ecommerce.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CriaNovoItemCarrinhoService implements CriaNovoItemCarrinhoUseCase {

    private final SaveItemCarrinhoPort saveItemCarrinhoPort;
    private final FindProdutoByIdPort findProdutoByIdPort;

    @Override
    public ItemDoCarrinho criaNovoItemDoCarrinho(Long idProduto, Integer quantidade, Carrinho carrinho) {

        Produto produtoBuscado = findProdutoByIdPort.findProdutoById(idProduto);
        ItemDoCarrinho novoItem = new ItemDoCarrinho(produtoBuscado, quantidade, carrinho);

        return saveItemCarrinhoPort.salvaItemNoCarrinho(novoItem);
    }
}
