package estudos.ecommerce.categoria.adapter.out.persistence;

import estudos.ecommerce.categoria.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByOrderByIdAsc();

    Optional<Categoria> findCategoriaByNome(String nome);
}
