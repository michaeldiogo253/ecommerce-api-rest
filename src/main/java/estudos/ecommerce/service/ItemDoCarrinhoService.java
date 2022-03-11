package estudos.ecommerce.service;

import estudos.ecommerce.exception.BussinessRuleException;
import estudos.ecommerce.exception.ResourceNotFoundException;
import estudos.ecommerce.model.Carrinho;
import estudos.ecommerce.model.ItemDoCarrinho;
import estudos.ecommerce.model.Produto;
import estudos.ecommerce.repository.CarrinhoRepository;
import estudos.ecommerce.repository.ItemDoCarrinhoRepository;
import estudos.ecommerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemDoCarrinhoService {

    private final ProdutoRepository produtoRepository;
    private final ItemDoCarrinhoRepository itemDoCarrinhoRepository;
    private final CarrinhoRepository carrinhoRepository;


    public ItemDoCarrinho buscaItemCarrinho(Carrinho carrinho, Long idProduto, Integer quantidade) {

        Optional<ItemDoCarrinho> itemDoCarrinhoBuscado =
                itemDoCarrinhoRepository.findItemCarrinhoByIdCarrinhoAndIdProduto(
                carrinho.getId(),
                idProduto);

        if (itemDoCarrinhoBuscado.isPresent()) {
            return itemDoCarrinhoBuscado.get();
        }

        return criaNovoItemDoCarrinho(idProduto, quantidade, carrinho);
    }


    public ItemDoCarrinho criaNovoItemDoCarrinho(Long idProduto, Integer quantidade, Carrinho carrinho) {

        Produto produto = produtoRepository.findById(idProduto)
                                           .orElseThrow(() -> new IllegalArgumentException("Produto não encntrado"));

        ItemDoCarrinho itemDoCarrinho = new ItemDoCarrinho(produto, quantidade, carrinho);
        itemDoCarrinhoRepository.save(itemDoCarrinho);
        return itemDoCarrinho;
    }


    public void removeProdutoCarrinho(Carrinho carrinho, Long idProduto, Integer quantidade) {

        ItemDoCarrinho itemDoCarrinhoBuscado = itemDoCarrinhoRepository.findItemCarrinhoByIdCarrinhoAndIdProduto(
                                                                               carrinho.getId(),
                                                                               idProduto)
                                                                       .orElseThrow(() -> new ResourceNotFoundException(
                                                                               "Produto não existe no carrinho"));

        if (!verificaSePodeSubtrairQuantidadeDoProduto(itemDoCarrinhoBuscado, quantidade)) {
            throw new BussinessRuleException(
                    "A quantidade informada para remoção é maior do que a quantidade de produtos que o carrinho " +
                    "possui");
        }

        itemDoCarrinhoBuscado.subtraiQuantidade(quantidade);

        if (seQuantidadeChegouAZeroDeletaDoCarrinho(itemDoCarrinhoBuscado)) {
            itemDoCarrinhoRepository.delete(itemDoCarrinhoBuscado);
            return;
        }

        itemDoCarrinhoRepository.save(itemDoCarrinhoBuscado);

    }

    public boolean verificaSePodeSubtrairQuantidadeDoProduto(ItemDoCarrinho itemDoCarrinhoBuscado,
                                                             Integer quantidadeASerSubtraida) {

        if (itemDoCarrinhoBuscado.getQuantidade() >= quantidadeASerSubtraida) {
            return true;
        }
        return false;
    }

    public boolean seQuantidadeChegouAZeroDeletaDoCarrinho(ItemDoCarrinho itemDoCarrinho) {

        return itemDoCarrinho.getQuantidade() == 0;
    }
}
