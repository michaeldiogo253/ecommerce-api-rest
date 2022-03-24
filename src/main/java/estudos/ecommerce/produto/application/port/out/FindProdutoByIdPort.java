package estudos.ecommerce.produto.application.port.out;

import estudos.ecommerce.produto.domain.Produto;

public interface FindProdutoByIdPort {

    Produto findProdutoById(Long idProduto);
}
