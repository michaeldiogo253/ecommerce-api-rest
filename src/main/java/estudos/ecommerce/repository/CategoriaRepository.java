package estudos.ecommerce.repository;

import estudos.ecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByOrderByIdAsc();

    Optional<Categoria> findCategoriaByNome(String nome);
}
