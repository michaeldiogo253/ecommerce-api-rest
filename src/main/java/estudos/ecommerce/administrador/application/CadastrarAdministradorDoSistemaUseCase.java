package estudos.ecommerce.administrador.application;

import estudos.ecommerce.administrador.application.port.SalvarAdministradorSistemaPort;
import estudos.ecommerce.administrador.domain.AdministradorDoSistema;
import estudos.ecommerce.usuario.adapter.persistence.PerfilRepository;
import estudos.ecommerce.usuario.adapter.persistence.UsuarioRepository;
import estudos.ecommerce.usuario.domain.Perfil;
import estudos.ecommerce.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CadastrarAdministradorDoSistemaUseCase {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final SalvarAdministradorSistemaPort salvarAdministradorSistemaPort;

    public AdministradorDoSistema execute(CadastrarAdministradorDoSistemaCommand command) {

        Perfil perfil = perfilRepository.findByNome("ROLE_ADMIN")
                                        .orElse(null);

        Usuario usuario = command.getUsuario();

        usuario.atribuiPerfil(perfil);

        usuarioRepository.save(usuario);

        AdministradorDoSistema administradorDoSistema =
                new AdministradorDoSistema(command.getDadosPessoais(),
                                           usuario);

        return salvarAdministradorSistemaPort.salvarAdministradorDoSistema(administradorDoSistema);

    }

}
