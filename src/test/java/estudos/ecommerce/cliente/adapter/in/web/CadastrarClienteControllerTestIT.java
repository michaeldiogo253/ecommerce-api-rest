package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.adapter.in.web.request.ClienteRequest;
import estudos.ecommerce.cliente.adapter.in.web.request.EnderecoRequest;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.usuario.request.UsuarioRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastrarClienteControllerTestIT {

    @Autowired private TestRestTemplate testRestTemplate;

    @Test
    void deveCadastrarNovoCliente() {

        EnderecoRequest enderecoRequest = new EnderecoRequest("Rua A", "10", "Brasil", "Brasil", "37775000", "casa");

        UsuarioRequest usuarioRequest = new UsuarioRequest("miguel", "12345", "miguel@gmail.com");
        ClienteRequest request = new ClienteRequest("Carlos",
                                                    "1236545477",
                                                    "35999869655",
                                                    "11-03-1997",
                                                    usuarioRequest,
                                                    enderecoRequest);

        ResponseEntity<Cliente> clienteResponseEntity = testRestTemplate.postForEntity(
                "/ecommerce-api/cliente/cadastrar",
                request,
                Cliente.class);

        assertThat(clienteResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(Objects.requireNonNull(clienteResponseEntity.getBody())
                          .getId()).isNotNull();

    }

}
