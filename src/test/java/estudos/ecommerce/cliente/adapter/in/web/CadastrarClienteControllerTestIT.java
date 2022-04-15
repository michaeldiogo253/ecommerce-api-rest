package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.adapter.in.web.request.ClienteRequest;
import estudos.ecommerce.cliente.domain.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastrarClienteControllerTestIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void deveCadastrarNovoCliente(){

        ClienteRequest request =  new ClienteRequest("Carlos", "1236545477", "35999869655", "11-03-1997");
        ResponseEntity<Cliente> clienteResponseEntity = testRestTemplate.postForEntity("/ecommerce-api/cliente/cadastrar",
                                                                                       request,
                                                                                       Cliente.class);
        assertThat(clienteResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(clienteResponseEntity.getBody().getId()).isNotNull();

    }

}