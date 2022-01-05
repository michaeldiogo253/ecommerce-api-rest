package estudos.ecommerce.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    @NotNull(message = "Campo nome não pode ser nulo")
    @NotBlank(message = "Campo nome não pode estar em branco")
    @NotEmpty(message = "Campo nome não pode estar vazio")
    private String nome;

    @NotNull(message = "Campo cpf não pode ser nulo")
    @NotBlank(message = "Campo cpf não pode estar em branco")
    @NotEmpty(message = "Campo cpf não pode estar vazio")
    private String cpf;

    @NotNull(message = "Campo telefone não pode ser nulo")
    @NotBlank(message = "Campo telefone não pode estar em branco")
    @NotEmpty(message = "Campo telefone não pode estar vazio")
    private String telefone;

    @NotNull(message = "Campo dataNascimento não pode ser nulo")
    @NotBlank(message = "Campo dataNascimento não pode estar em branco")
    @NotEmpty(message = "Campo dataNascimento não pode estar vazio")
    private String dataNasc;
}
