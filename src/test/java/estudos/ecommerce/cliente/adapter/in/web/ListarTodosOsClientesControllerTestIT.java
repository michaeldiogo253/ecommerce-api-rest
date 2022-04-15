package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.adapter.in.web.response.ClienteResponse;
import estudos.ecommerce.cliente.application.port.in.ListarTodosOsClientesUseCase;
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
class ListarTodosOsClientesControllerTestIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    // o retorno é do tipo string, pois o retorno é
    @Test
    void deveListarTodosOsClientesEDevolverStatus200(){

        var clienteResponseEntity = testRestTemplate.getForEntity("/ecommerce-api/cliente/listar-todos",
                                                                                      String.class);

        assertThat(clienteResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}