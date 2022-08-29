package estudos.ecommerce.cliente.adapter.in.web.request;

import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.cliente.domain.DadosPessoais;
import estudos.ecommerce.usuario.request.UsuarioRequest;
import estudos.ecommerce.util.data.ConversorDeDatas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "Campo nome é obrigatorio") private String nome;

    @NotBlank(message = "Campo cpf é obrigatorio") @CPF(message = "O CPF está no formato inválido.") private String cpf;

    @NotBlank(message = "Campo telefone é obrigatorio") private String telefone;

    @NotBlank(message = "Campo dataNascimento é obrigatorio") private String dataNasc;

    @NotNull(message = "Usuario Request é obrigatorio!") private UsuarioRequest usuarioRequest;

    @NotNull(message = "Endereco é obrigatorio")
    EnderecoRequest enderecoRequest;

    public Cliente toModel() {

        return new Cliente(this.getNome(),
                           this.getCpf(),
                           this.getTelefone(),
                           ConversorDeDatas.converteDataStringParaLocalDate(this.dataNasc),
                           enderecoRequest.toModel(),
                           usuarioRequest.toModel());
    }

    public DadosPessoais toDadosPessoais(ClienteRequest request) {

        return new DadosPessoais(request.nome,
                                 request.cpf,
                                 request.telefone,
                                 ConversorDeDatas.converteDataStringParaLocalDate(request.dataNasc));
    }

    public String getCpf() {

        return this.cpf.replace(" ", "")
                       .replace("-", "")
                       .replace(".", "");
    }

}
