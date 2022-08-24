package estudos.ecommerce.administrador.adapter.in.web.request;

import estudos.ecommerce.usuario.request.UsuarioRequest;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class CadastrarAdministradorRequest {

    @NotBlank(message = "Campo nome é obrigatorio") String nome;

    @NotBlank(message = "Campo cpf é obrigatorio") @CPF(message = "O CPF está no formato inválido.") String cpf;

    @NotBlank(message = "Campo telefone é obrigatorio") String telefone;

    @NotBlank(message = "Campo dataNascimento é obrigatorio") String dataNasc;

    @NotNull(message = "Usuario Request é obrigatorio!") UsuarioRequest usuarioRequest;

}
