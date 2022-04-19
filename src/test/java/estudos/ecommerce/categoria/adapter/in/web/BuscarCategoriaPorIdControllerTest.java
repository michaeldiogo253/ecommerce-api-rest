package estudos.ecommerce.categoria.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import estudos.ecommerce.categoria.adapter.in.web.response.CategoriaResponse;
import estudos.ecommerce.categoria.application.port.out.FindCategoriaByIdPort;
import estudos.ecommerce.categoria.domain.Categoria;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BuscarCategoriaPorIdController.class)
@AutoConfigureMockMvc(addFilters = false)
class BuscarCategoriaPorIdControllerTest {

    @MockBean private FindCategoriaByIdPort findCategoriaByIdPort;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;

    @Test
    void deveListarCategoriaComIdValidoERetornar200() throws Exception {
        Long idCategoria = 1L;
        String url = "/ecommerce-api/categoria/{idCategoria}";

        Categoria categoria = new Categoria("ELETRONICOS");

        given(findCategoriaByIdPort.findCategoriaByIdPort(idCategoria)).willReturn(categoria);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url, idCategoria)
                                                                 .header("Content-Type", "application/json")
                                                                 .accept("application/json"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        then(findCategoriaByIdPort).should().findCategoriaByIdPort(idCategoria);

        assertThat(result.getResponse().getContentAsString()).isEqualTo(mapper.writeValueAsString(CategoriaResponse.from(categoria)));

    }

}