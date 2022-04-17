package estudos.ecommerce.produto.adapter.in.web;

import estudos.ecommerce.produto.application.port.out.FindAllProdutoPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListarTodosOsProdutosControllerTestIT {

    @Autowired
    private  TestRestTemplate testRestTemplate;

    @Autowired
    private  FindAllProdutoPort findAllProdutoPort;

    @Test
    void deveListarTodosOsProdutos(){

        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/ecommerce-api/produto/listar-todos",
                                                                         String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}