package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.application.port.in.ListarClientePorIdControllerUseCase;
import estudos.ecommerce.cliente.domain.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListarClientePorIdControllerTestIT {

    @Autowired private TestRestTemplate testRestTemplate;

    @Test
    void deveListarClinteComIdIgualA1() {
    // poderia utilizar o Cliente.class ao invés do String

        Long idCliente = 1L;

        var clienteResponse = testRestTemplate.getForEntity("/ecommerce-api/cliente/{id}",
                                                            String.class,
                                                            idCliente);

        assertThat(clienteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void deveDevolver500AoListarClinteComIdInvalido() {

        Long idCliente = 100000L;

        ResponseEntity<Cliente> clienteResponse = testRestTemplate.getForEntity("/ecommerce-api/cliente/{id}",
                                                                                Cliente.class,
                                                                                idCliente);

        assertThat(clienteResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}