package estudos.ecommerce.produto.application.port.in;

import estudos.ecommerce.produto.domain.Produto;

public interface CadastrarProdutoUseCase {

    Produto execute(Produto produto, String nomeCategoria);
}
