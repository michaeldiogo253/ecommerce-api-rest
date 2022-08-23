package estudos.ecommerce.usuario.adapter.persistence;

import estudos.ecommerce.usuario.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query("select p from Perfil p where p.nome = ?1")
    Optional<Perfil> findByNome(String nome);

}
