package estudos.ecommerce.controller.request;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CarrinhoRequest {

    @NotNull(message = "Id do cliente é obrigatorio") Long idCliente;
    @NotNull(message = "Id do produto é obrigatorio") Long idProduto;
    @NotNull(message = "Quantidade do produto é obrigatoria") Integer quantidade;

}
