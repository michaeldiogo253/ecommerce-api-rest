package estudos.ecommerce.produto.application.port.in;

import estudos.ecommerce.produto.domain.Produto;

import java.math.BigDecimal;

public interface AtualizarProdutoUseCase {

    Produto execute(Long idProduto, String nome, String descricao, BigDecimal preco);
}
