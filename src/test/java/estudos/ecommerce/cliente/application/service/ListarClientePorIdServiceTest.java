package estudos.ecommerce.cliente.application.service;

import estudos.ecommerce.cliente.application.port.out.FindClienteByIdPort;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.databuilders.ClienteCreator;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

class ListarClientePorIdServiceTest {

    private final FindClienteByIdPort findClienteByIdPort = Mockito.mock(FindClienteByIdPort.class);
    private final ListarClientePorIdService service =  new ListarClientePorIdService(findClienteByIdPort);

    @Test
    void deveListarClinteComIdValido(){

        Long idCliente = 1L;
        Cliente cliente = ClienteCreator.umClienteAleatorio();

        given(findClienteByIdPort.buscaClientePorId(idCliente)).willReturn(cliente);

        Cliente clienteBuscado = service.execute(idCliente);

        then(findClienteByIdPort).should().buscaClientePorId(idCliente);

        assertThat(clienteBuscado.getNome()).isEqualTo(cliente.getNome());

    }

    @Test
    void deveLançarExceptionaoListarClinteComIdInvalido(){

        Long idCliente = 999999L;

        doThrow( new ResourceNotFoundException("Categoria não encontrada"))
                .when(findClienteByIdPort).buscaClientePorId(idCliente);

        assertThatThrownBy(() -> {
            service.execute(idCliente);
        }).isInstanceOf(ResourceNotFoundException.class);


        then(findClienteByIdPort).should().buscaClientePorId(idCliente);


    }

}