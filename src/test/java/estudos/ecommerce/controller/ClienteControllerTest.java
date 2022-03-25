package estudos.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import estudos.ecommerce.cliente.adapter.in.web.request.ClienteRequest;
import estudos.ecommerce.cliente.adapter.in.web.response.ClienteResponse;
import estudos.ecommerce.databuilders.ClienteCreator;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClienteController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClienteControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean ClienteService clienteService;

    @Autowired private ObjectMapper mapper;

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

    @Test
    void deveListarTodosClientes() throws Exception {

        List<Cliente> clientesAleatorios = ClienteCreator.geraClientes(3);

        List<ClienteResponse> responses = ClienteResponse.from(clientesAleatorios);
        given(clienteService.listarTodosOsClientes()).willReturn(clientesAleatorios);

        String url = "/ecommerce-api/cliente/listar-todos";

        MvcResult result = mockMvc.perform(get(url, "1").header("Content-Type", "application/json")
                                                        .accept("application/json"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        then(clienteService).should()
                            .listarTodosOsClientes();

        String expected = mapper.writeValueAsString(responses);
        assertThat(result.getResponse()
                         .getContentAsString()).isEqualTo(expected);
    }

    @Test
    void deveListarUmClientePeloId() throws Exception {

        Long idCliente = 1L;
        Cliente cliente = ClienteCreator.umClienteAleatorio();
        ClienteResponse response = new ClienteResponse(cliente);

        given(clienteService.buscarClientePorId(idCliente)).willReturn(Optional.of(cliente));

        String url = "/ecommerce-api/cliente/{id}";

        MvcResult result = mockMvc.perform(get(url, "1", idCliente).header("Content-Type", "application/json")
                                                                   .accept("application/json"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        then(clienteService).should()
                            .buscarClientePorId(idCliente);

        String expected = mapper.writeValueAsString(response);
        assertThat(result.getResponse()
                         .getContentAsString()).isEqualTo(expected);

    }

    @Test
    void deveriaLancarExcecaoAoBuscarClienteComIdInvalido() {

        Long idCliente = 1L;
        given(clienteService.buscarClientePorId(idCliente)).willReturn(Optional.of(new Cliente()));
    }

    @Test
    void deveriaExcluirUmClientePeloId() throws Exception {

        Long idCliente = 1L;
        Cliente cliente = ClienteCreator.umClienteAleatorio();
        cliente.setId(idCliente);
        ClienteResponse response = new ClienteResponse(cliente);

        //given(clienteService.deletarClientePorId(idCliente)).willReturn((ResponseEntity<Void>) ResponseEntity
        // .status(HttpStatus.NO_CONTENT));

        String url = "/ecommerce-api/cliente/{id}";
        MvcResult result = mockMvc.perform(delete(url, idCliente).header("Content-Type", "application/json")
                                                                 .accept("application/json"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        then(clienteService).should()
                            .deletarClientePorId(idCliente);
    }

    @Test
    void deveriaCadastrarUmCliente() throws Exception {

        ClienteRequest request = new ClienteRequest("Michael", "119481", "359997826987", "1995-01-17");
        Cliente cliente = ClienteCreator.gerarUmCliente("Michael", "119481", "359997826987", LocalDate.of(1995, 1, 17));
        cliente.setId(1L);


        given(clienteService.cadastrarCliente(request)).willReturn(cliente);
        given(cliente.getId()).willReturn(1L);

        String url = "/ecommerce-api/cliente";

        MvcResult result = mockMvc.perform(post("/ecommerce-api/cliente").header("Content-Type", "application/json")
                                                    .accept("application/json")
                                                    .content(mapper.writeValueAsString(request)))
                                  .andExpect(status().isCreated())
                                  .andReturn();

        then(clienteService).should()
                            .cadastrarCliente(request);
    }
}