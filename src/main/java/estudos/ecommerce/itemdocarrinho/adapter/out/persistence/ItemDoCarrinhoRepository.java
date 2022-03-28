package estudos.ecommerce.itemdocarrinho.adapter.out.persistence;

import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemDoCarrinhoRepository extends JpaRepository<ItemDoCarrinho, Long> {

    @Query("SELECT i FROM ItemDoCarrinho i where i.carrinho.id = :idCarrinho AND i.produto.id = :idProduto")
    Optional<ItemDoCarrinho> findItemCarrinhoByIdCarrinhoAndIdProduto(@Param("idCarrinho") Long idCarrinho,
                                                                      @Param("idProduto") Long idProduto);

    @Query("SELECT i FROM ItemDoCarrinho i where i.id = :idItem AND i.produto.id = :idProduto")
    Optional<ItemDoCarrinho> findItemCarrinhoByIdItemAndProdutoId(@Param("idItem") Long idItem,
                                                                      @Param("idProduto") Long idProduto);
}
