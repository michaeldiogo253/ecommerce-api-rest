package estudos.ecommerce.produto.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import estudos.ecommerce.produto.adapter.in.web.request.ProdutoRequest;
import estudos.ecommerce.produto.domain.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CadastrarProdutoControllerTestIT {

    @Autowired TestRestTemplate testRestTemplate;

    @Autowired MockMvc mock;

    @Autowired ObjectMapper mapper;

    @Test
    void deveriaCadastrarUmNovoProduto() throws Exception {

        ProdutoRequest request = new ProdutoRequest("Fone de ouvido bluetooh 3",
                                                    "fone",
                                                    new BigDecimal("100"),
                                                    "INFORMATICA");

        ResponseEntity<Produto> template = testRestTemplate.postForEntity("/ecommerce-api/produto/cadastrar",
                                                                          request,
                                                                          Produto.class);

        assertThat(template.getBody().getNome()).isEqualTo(request.getNome());
        assertThat(template.getBody().getDescricao()).isEqualTo(request.getDescricao());
        assertThat(template.getBody().getPreco()).isEqualTo(request.getPreco());
        assertThat(template.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(template.getBody().getId()).isNotNull();

    }
}