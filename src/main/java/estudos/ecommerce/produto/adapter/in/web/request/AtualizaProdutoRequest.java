package estudos.ecommerce.produto.adapter.in.web.request;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class AtualizaProdutoRequest {

    @NotNull(message = "Id do produto é obrigatorio") Long idProduto;
    @NotBlank(message = "Nome do produto é obrigatorio") String nome;
    @NotBlank(message = "Descrição do produto é obrigatoria") String descricao;
    @NotNull(message = "Preço do produto é obrigatorio") BigDecimal preco;
}
