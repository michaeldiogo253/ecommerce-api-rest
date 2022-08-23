package estudos.ecommerce.carrinho.adapter.in.web.response;

import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.adapter.in.web.response.ItemDoCarrinhoResponse;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class CarrinhoResponse {

    Long idCarrinho;
    String nomeCliente;
    List<ItemDoCarrinhoResponse> itens;
    BigDecimal total;
    Integer quantidadeTotalDeItens;

    public static CarrinhoResponse from(Carrinho carrinho) {

        return new CarrinhoResponse(carrinho.getId(),
                                    carrinho.getNomeCliente(),
                                    ItemDoCarrinhoResponse.of(carrinho.getItemDoCarrinhos()),
                                    carrinho.getTotal(),
                                    carrinho.getQuantidadeDeItensDoCarrinho());
    }

}
