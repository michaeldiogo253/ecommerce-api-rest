package estudos.ecommerce.produto.adapter.out.persistence;

import estudos.ecommerce.produto.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
