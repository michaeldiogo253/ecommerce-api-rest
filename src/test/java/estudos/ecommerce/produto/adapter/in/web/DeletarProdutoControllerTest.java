package estudos.ecommerce.produto.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import estudos.ecommerce.produto.application.service.DeletarProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DeletarProdutoController.class)
@AutoConfigureMockMvc(addFilters = false)
class DeletarProdutoControllerTest {

    @MockBean private DeletarProdutoService deletarProdutoService;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;

    @Test
    void deveDeletarProdutoComIdValido() throws Exception {

        Long idProduto = 1L;
        String url = "/ecommerce-api/produto/{idProduto}";

        mockMvc.perform(MockMvcRequestBuilders.delete(url, idProduto)
                                              .header("Content-Type", "application/json")
                                              .accept("application/json"))
               .andExpect(status().isOk())
               .andReturn();

        then(deletarProdutoService).should()
                                   .execute(idProduto);
    }

}