package estudos.ecommerce.config;

import estudos.ecommerce.usuario.adapter.persistence.UsuarioRepository;
import estudos.ecommerce.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = repository.findByLogin(username);

        if (usuario.isPresent()) {
            return usuario.get();
    }

        throw new UsernameNotFoundException("Dados inválidos!");
    }

}