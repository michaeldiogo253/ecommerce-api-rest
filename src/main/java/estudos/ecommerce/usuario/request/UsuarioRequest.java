package estudos.ecommerce.usuario.request;

import estudos.ecommerce.usuario.domain.Usuario;
import lombok.Value;

@Value
public class UsuarioRequest {

    String login;
    String senha;
    String email;

    public Usuario toModel(){
        return new Usuario(this.login, this.senha, this.email);
    }

}
