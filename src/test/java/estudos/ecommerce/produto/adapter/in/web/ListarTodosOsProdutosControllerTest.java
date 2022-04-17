package estudos.ecommerce.produto.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import estudos.ecommerce.databuilders.ProdutoCreator;
import estudos.ecommerce.produto.adapter.in.web.response.ProdutoResponse;
import estudos.ecommerce.produto.application.port.out.FindAllProdutoPort;
import estudos.ecommerce.produto.domain.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ListarTodosOsProdutosController.class)
@AutoConfigureMockMvc(addFilters = false)
class ListarTodosOsProdutosControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper mapper;

    @MockBean private FindAllProdutoPort findAllProdutoPort;

    @Test
    void deveListarTodosOsProdutosEDevolverStatus200() throws Exception {

        String url = "/ecommerce-api/produto/listar-todos";

        List<Produto> produtos = ProdutoCreator.variosProdutos(3);
        List<ProdutoResponse> produtoResponses = ProdutoResponse.from(produtos);

        given(findAllProdutoPort.listarTodosOsProdutos()).willReturn(produtos);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                                                                 .header("Content-Type", "application/json")
                                                                 .accept("application/json"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        then(findAllProdutoPort).should()
                                .listarTodosOsProdutos();

        assertThat(result.getResponse()
                         .getContentAsString()).isEqualTo(mapper.writeValueAsString(produtoResponses));

    }

}