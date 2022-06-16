package estudos.ecommerce.carrinho.adapter.in.web;

import estudos.ecommerce.carrinho.adapter.in.web.request.CarrinhoRequest;
import estudos.ecommerce.carrinho.adapter.in.web.response.CarrinhoResponse;
import estudos.ecommerce.carrinho.application.port.in.AdicionaProdutoNoCarrinhoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdicionaProdutoNoCarrinhoControllerTestIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AdicionaProdutoNoCarrinhoUseCase adicionarProdutoNoCarrinhoUseCase;

    @Test
    void deveriaAdicionaProdutoNoCarrinhoEDevolverStatusCode201(){

        CarrinhoRequest request =  new CarrinhoRequest(6L, 1L, 10);
        ResponseEntity<CarrinhoResponse> carrinhoResponseEntity =
                testRestTemplate.postForEntity("/ecommerce-api/carrinho/adicionar-produto",
                                               request, CarrinhoResponse.class);

        assertThat(carrinhoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(carrinhoResponseEntity.getBody().getIdCarrinho()).isNotNull();

    }
}