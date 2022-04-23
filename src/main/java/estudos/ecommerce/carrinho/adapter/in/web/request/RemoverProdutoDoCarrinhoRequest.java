package estudos.ecommerce.carrinho.adapter.in.web.request;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class RemoverProdutoDoCarrinhoRequest {

    @NotNull(message = "Id do cliente é obrigatorio") Long idCliente;
    @NotNull(message = "Id do produto é obrigatorio") Long idProduto;
}
