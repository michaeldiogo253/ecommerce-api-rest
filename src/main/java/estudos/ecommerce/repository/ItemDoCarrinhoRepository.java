package estudos.ecommerce.repository;

import estudos.ecommerce.carrinho.domain.ItemDoCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemDoCarrinhoRepository extends JpaRepository<ItemDoCarrinho, Long> {

    @Query("SELECT i FROM ItemDoCarrinho i where i.carrinho.id = :idCarrinho AND i.produto.id = :idProduto")
    Optional<ItemDoCarrinho> findItemCarrinhoByIdCarrinhoAndIdProduto(@Param("idCarrinho") Long idCarrinho,
                                                                      @Param("idProduto") Long idProduto);

    @Query("select i from ItemDoCarrinho i where i.produto.id = ?1 and i.carrinho.id = ?2")
    Optional<ItemDoCarrinho> findByProduto_IdAndCarrinho_Id(Long id, Long id1);
}
