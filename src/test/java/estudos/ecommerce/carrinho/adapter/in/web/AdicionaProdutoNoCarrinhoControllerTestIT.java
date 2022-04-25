package estudos.ecommerce.carrinho.adapter.in.web;

import estudos.ecommerce.carrinho.application.port.in.AdicionaProdutoNoCarrinhoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdicionaProdutoNoCarrinhoControllerTestIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AdicionaProdutoNoCarrinhoUseCase adicionarProdutoNoCarrinhoUseCase;

    @Test
    void deveriaAdicionaProdutoNoCarrinhoEDevolverStatusCode201(){

    }
}