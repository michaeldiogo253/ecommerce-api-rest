package estudos.ecommerce.produto.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import estudos.ecommerce.produto.domain.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BuscarProdutoPorIdControllerTestIT {

    @Autowired TestRestTemplate testRestTemplate;

    @Autowired MockMvc mock;

    @Autowired ObjectMapper mapper;

    @Test
    void deveriaRetornar200AoListarProdutoComId1() throws Exception {
        URI uri = new URI("/ecommerce-api/produto/1");
        ResultActions resultActions = mock.perform(MockMvcRequestBuilders.get(uri)
                                                                         .contentType(MediaType.APPLICATION_JSON))
                                          .andExpect(MockMvcResultMatchers.status()
                                                                          .is(200));

        resultActions.andReturn().getResponse().getContentAsString();
    }

    @Test
    void deveriaListarProdutoComIdValidoERetornar200() throws Exception {

        ResponseEntity<Produto> produtoBuscado = testRestTemplate.getForEntity("/ecommerce-api/produto/1", Produto.class);
        assertThat(produtoBuscado.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(produtoBuscado.getBody().getId()).isEqualTo(1L);
    }


}