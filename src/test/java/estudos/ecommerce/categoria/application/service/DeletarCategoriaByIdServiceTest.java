package estudos.ecommerce.categoria.application.service;

import estudos.ecommerce.categoria.application.port.out.DeletarCategoriaByIdPort;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class DeletarCategoriaByIdServiceTest {

    private final DeletarCategoriaByIdPort deletarCategoriaByIdPort = Mockito.mock(DeletarCategoriaByIdPort.class);
    private final DeletarCategoriaByIdService deletarCategoriaByIdService = new DeletarCategoriaByIdService(
            deletarCategoriaByIdPort);

    @Test
    void deveDeletarCategoriaComIdValido(){

        deletarCategoriaByIdService.execute(1L);

        then(deletarCategoriaByIdPort).should().deletarCategoriaPorId(1L);

    }

    @Test
    void deveLancarResourceNotFoundExceptionAoDeletarCategoriaComIdInexistente(){

        Long idCategoriaInvalido = 123485L;

        doThrow( new ResourceNotFoundException("Categoria não encontrada"))
                .when(deletarCategoriaByIdPort).deletarCategoriaPorId(idCategoriaInvalido);

        assertThatThrownBy(() -> {
            deletarCategoriaByIdService.execute(idCategoriaInvalido);
        }).isInstanceOf(ResourceNotFoundException.class);

        then(deletarCategoriaByIdPort).should().deletarCategoriaPorId(idCategoriaInvalido);
    }

}