package estudos.ecommerce.produto.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import estudos.ecommerce.categoria.domain.Categoria;
import estudos.ecommerce.produto.adapter.in.web.request.ProdutoRequest;
import estudos.ecommerce.produto.application.port.in.CadastrarProdutoUseCase;
import estudos.ecommerce.produto.domain.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CadastrarProdutoController.class)
@AutoConfigureMockMvc(addFilters = false)
class CadastrarProdutoControllerTest {

    @MockBean private CadastrarProdutoUseCase cadastrarProdutoUseCase;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;

    @Test
    void deveriaCadastrarProtudoEDevolver201() throws Exception {

        String url = "/ecommerce-api/produto/cadastrar";

        ProdutoRequest request = new ProdutoRequest("Celular Nokia",
                                                    "smartphone",
                                                    new BigDecimal("1000"),
                                                    "Informatica");

        Produto produtoCriado = new Produto("Celular Nokia",
                                            "smartphone",
                                            new BigDecimal("1000"),
                                            new Categoria("Informatica"));

        given(cadastrarProdutoUseCase.execute(any(), anyString())).willReturn(produtoCriado);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                 .header("Content-Type", "application/json")
                                                                 .accept("application/json")
                                                                 .content(mapper.writeValueAsString(request)))
                                  .andExpect(status().isCreated())
                                  .andReturn();

        then(cadastrarProdutoUseCase).should()
                                     .execute(any(), anyString());

        assertThat(result.getResponse()
                         .getContentAsString()).isEqualTo("{\"id\":null,\"nome\":\"Celular Nokia\"," +
                                                          "\"descricao\":\"smartphone\",\"preco\":1000," +
                                                          "\"nomeCategoria\":\"INFORMATICA\"}");

    }

    @Test
    void deveriaRetornar400BadRequestQuandoNomeDoProdutoNaRequestEstiverEmBranco() throws Exception {

        String url = "/ecommerce-api/produto/cadastrar";

        ProdutoRequest request = new ProdutoRequest("", "smartphone", new BigDecimal("1000"), "Informatica");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                                                                 .header("Content-Type", "application/json")
                                                                 .accept("application/json")
                                                                 .content(mapper.writeValueAsString(request)))
                                  .andExpect(status().isBadRequest())
                                  .andReturn();

    }

}
