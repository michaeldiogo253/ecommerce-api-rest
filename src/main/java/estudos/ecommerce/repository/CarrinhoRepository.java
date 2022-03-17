package estudos.ecommerce.repository;

import estudos.ecommerce.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    @Query("SELECT c FROM Carrinho c WHERE cliente.id = :idCliente")
    Optional<Carrinho> findByIdCliente(@Param("idCliente")Long idCliente);
}
