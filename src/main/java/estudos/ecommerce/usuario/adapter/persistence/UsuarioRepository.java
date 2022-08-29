package estudos.ecommerce.usuario.adapter.persistence;

import estudos.ecommerce.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.login = ?1")
    Optional<Usuario> findByLogin(String login);

}
