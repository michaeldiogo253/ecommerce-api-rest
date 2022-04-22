package estudos.ecommerce.databuilders;

import estudos.ecommerce.categoria.domain.Categoria;
import estudos.ecommerce.produto.domain.Produto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProdutoCreator {

    public static Produto umProduto() {

        return new Produto("ANY_PRODUTO",
                           "ANY_DESCRICAO",
                           new BigDecimal("10"),
                           new Categoria("INFORMATICA"));
    }

    public static List<Produto> variosProdutos(Integer quantidade) {

        return quantidade > 0 ? IntStream.range(0, quantidade)
                                         .mapToObj(value -> umProduto())
                                         .collect(Collectors.toList()) : Collections.emptyList();
    }

}
