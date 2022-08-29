package estudos.ecommerce.config.controller.request;

import lombok.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Value
public class LoginRequest {

    @NotBlank(message = "Login não pode ser nulo") String login;
    @NotBlank(message = "Senha não pode ser nula") String senha;

    public UsernamePasswordAuthenticationToken converter() {

        return new UsernamePasswordAuthenticationToken(login, senha);
    }

}
