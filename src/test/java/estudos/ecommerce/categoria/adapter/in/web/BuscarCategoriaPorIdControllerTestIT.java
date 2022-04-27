package estudos.ecommerce.categoria.adapter.in.web;

import estudos.ecommerce.categoria.adapter.in.web.response.CategoriaResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BuscarCategoriaPorIdControllerTestIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void deveBuscarCategoriaComIdValidoERetornarStatus200OK(){

        var idCategoria = 1L;

        var entity =
               testRestTemplate.getForEntity("/ecommerce-api/categoria/{idCategoria}",
                                             CategoriaResponse.class,
                                             idCategoria);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(entity.getBody())
                          .getId()).isEqualTo(idCategoria);

    }

    @Test
    void deveDevolver500AoListarCategoriaComIdInvalido() {

        var idCategoria = 100000L;

        var categoriaResponse = testRestTemplate.getForEntity("/ecommerce-api/categoria/{idCategoria}",
                                                                                CategoriaResponse.class,
                                                                                idCategoria);

        assertThat(categoriaResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}