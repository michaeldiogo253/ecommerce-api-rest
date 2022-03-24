package estudos.ecommerce.produto.adapter.in.web.response;

import estudos.ecommerce.produto.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {

    Long id;
    String nome;
    String descricao;
    BigDecimal preco;
    String nomeCategoria;

    public static ProdutoResponse from(Produto produto) {

        return new ProdutoResponse(produto.getId(),
                                   produto.getNome(),
                                   produto.getDescricao(),
                                   produto.getPreco(),
                                   produto.getNomeCategoria());
    }

    public static List<ProdutoResponse> from(List<Produto> produtos) {

        return produtos.stream().map(ProdutoResponse::from).collect(Collectors.toList());
    }
}
