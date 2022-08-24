package estudos.ecommerce.administrador.application;

import estudos.ecommerce.cliente.domain.DadosPessoais;
import estudos.ecommerce.usuario.domain.Usuario;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CadastrarAdministradorDoSistemaCommand {

    private DadosPessoais dadosPessoais;

    private Usuario usuario;

}
