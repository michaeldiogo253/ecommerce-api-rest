package estudos.ecommerce.itemdocarrinho.adapter.in.web.response;

import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class ItemDoCarrinhoResponse {

    Long idItemCarrinho;
    Long idProduto;
    String nome;
    String categoria;
    String descricao;
    BigDecimal preco;
    Integer quantidade;

    public static ItemDoCarrinhoResponse of(ItemDoCarrinho itemDoCarrinho){
        return new ItemDoCarrinhoResponse(itemDoCarrinho.getId(),
                                          itemDoCarrinho.getProdutoId(),
                                          itemDoCarrinho.getProduto().getNome(),
                                          itemDoCarrinho.getProduto().getNomeCategoria(),
                                          itemDoCarrinho.getProduto().getDescricao(),
                                          itemDoCarrinho.getProduto().getPreco(),
                                          itemDoCarrinho.getQuantidade());
    }

    public static List<ItemDoCarrinhoResponse> of(List<ItemDoCarrinho> itensDoCarrinho){

      return itensDoCarrinho.stream().map(ItemDoCarrinhoResponse::of).collect(Collectors.toList());
    }

}
