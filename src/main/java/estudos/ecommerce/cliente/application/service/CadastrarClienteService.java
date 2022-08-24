package estudos.ecommerce.cliente.application.service;

import estudos.ecommerce.cliente.application.port.in.CadastrarClienteUseCase;
import estudos.ecommerce.cliente.application.port.out.SaveClientePort;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.usuario.adapter.persistence.PerfilRepository;
import estudos.ecommerce.usuario.adapter.persistence.UsuarioRepository;
import estudos.ecommerce.usuario.domain.Perfil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CadastrarClienteService implements CadastrarClienteUseCase {

    private final SaveClientePort salvarClientePort;
    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Cliente execute(Cliente cliente) {

        Perfil perfil = perfilRepository.findByNome("ROLE_CLIENTE")
                                        .orElse(null);

        usuarioRepository.save(cliente.getUsuario());

        cliente.atribuiPerfil(perfil);

        return salvarClientePort.salvarCliente(cliente);
    }
}