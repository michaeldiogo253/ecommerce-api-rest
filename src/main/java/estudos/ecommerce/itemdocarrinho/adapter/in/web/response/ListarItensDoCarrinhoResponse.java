package estudos.ecommerce.itemdocarrinho.adapter.in.web.response;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;


@Value
public class ListarItensDoCarrinhoResponse {

    Long idCarrinho;
    String nomeCliente;
    BigDecimal total;
    Integer quantidadeTotalDeItens;
    List<ItemDoCarrinhoResponse> itensDoCarrinhoResponse;

}
