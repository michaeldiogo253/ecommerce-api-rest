package estudos.ecommerce.carrinho.adapter.out.persistence;

import estudos.ecommerce.carrinho.domain.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    @Query("SELECT c FROM Carrinho c WHERE cliente.id = :idCliente")
    Optional<Carrinho> findByIdCliente(@Param("idCliente")Long idCliente);
}
