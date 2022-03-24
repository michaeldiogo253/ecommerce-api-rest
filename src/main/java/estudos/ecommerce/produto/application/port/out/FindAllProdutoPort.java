package estudos.ecommerce.produto.application.port.out;

import estudos.ecommerce.produto.domain.Produto;

import java.util.List;

public interface FindAllProdutoPort {

    List<Produto> listarTodosOsProdutos();
}
