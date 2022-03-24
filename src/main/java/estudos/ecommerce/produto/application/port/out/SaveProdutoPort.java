package estudos.ecommerce.produto.application.port.out;

import estudos.ecommerce.produto.domain.Produto;

public interface SaveProdutoPort {

    Produto salvarProduto(Produto produto);
}
