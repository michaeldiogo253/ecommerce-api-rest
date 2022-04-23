package estudos.ecommerce.categoria.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import estudos.ecommerce.categoria.adapter.in.web.request.AlterarCategoriaRequest;
import estudos.ecommerce.categoria.application.port.in.AlterarCategoriaUseCase;
import estudos.ecommerce.categoria.domain.Categoria;
import estudos.ecommerce.databuilders.CategoriaCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlterarCategoriaController.class)
@AutoConfigureMockMvc(addFilters = false)
class AlterarCategoriaControllerTest {

    @MockBean private AlterarCategoriaUseCase useCase;
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;

    @Test
    void deveriaAlterarONomeDeUmaCategoriaERetornar200() throws Exception {

        String url = "/ecommerce-api/categoria/atualizar";
        Categoria categoria = CategoriaCreator.umaCategoria("brinquedos");
        AlterarCategoriaRequest request = new AlterarCategoriaRequest(1L, "brinquedos");

        given(useCase.execute(anyLong(), anyString())).willReturn(categoria);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(url)
                                                                 .header("Content-Type", "application/json")
                                                                 .accept("application/json")
                                                                 .content(mapper.writeValueAsString(request)))
                                  .andExpect(status().isOk())
                                  .andReturn();

        then(useCase).should().execute(request.getIdCategoria(), request.getNome());

        assertThat(result.getResponse().getContentAsString()).isEqualTo("{\"id\":null,\"nome\":\"BRINQUEDOS\"}");
    }

    @Test
    void deveriaRetornar400BadRequestQuandoNapPrencherTodosOsAtributosDaRequest() throws Exception {
        String url = "/ecommerce-api/categoria/atualizar";
        AlterarCategoriaRequest request = new AlterarCategoriaRequest();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(url)
                                                                 .header("Content-Type", "application/json")
                                                                 .accept("application/json")
                                                                 .content(mapper.writeValueAsString(request)))
                                  .andExpect(status().isBadRequest())
                                  .andReturn();

        then(useCase).shouldHaveNoInteractions();
    }
}