package estudos.ecommerce.controller.request;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Value
public class ProdutoRequest {

    @NotNull @NotEmpty @NotBlank(message = "Nome do produto é obrigatorio") String nome;
    @NotNull @NotEmpty @NotBlank(message = "Descrição do produto é obrigatoria") String descricao;
    @NotNull(message = "Preço do produto é obrigatorio") BigDecimal preco;
    @NotNull @NotEmpty @NotBlank(message = "Categoria do produto é obrigatorio") String categoria;

}
