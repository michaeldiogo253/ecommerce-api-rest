package estudos.ecommerce.produto.adapter.in.web.request;

import estudos.ecommerce.produto.domain.Produto;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class ProdutoRequest {

    @NotBlank(message = "Nome do produto é obrigatorio") String nome;
    @NotBlank(message = "Descrição do produto é obrigatoria") String descricao;
    @NotNull(message = "Preço do produto é obrigatorio") BigDecimal preco;
    @NotBlank(message = "Categoria do produto é obrigatorio") String categoria;

    public Produto toModel(ProdutoRequest request) {
        return new Produto(request.getNome(), request.getDescricao(), request.getPreco());
    }

}
