package estudos.ecommerce.categoria.application.service;

import estudos.ecommerce.categoria.application.port.out.FindCategoriaByIdPort;
import estudos.ecommerce.categoria.application.port.out.SaveCategoriaPort;
import estudos.ecommerce.categoria.domain.Categoria;
import estudos.ecommerce.databuilders.CategoriaCreator;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

class AlterarCategoriaServiceTest {

    private final FindCategoriaByIdPort findCategoriaByIdPort = Mockito.mock(FindCategoriaByIdPort.class);
    private final SaveCategoriaPort saveCategoriaPort = Mockito.mock(SaveCategoriaPort.class);

    private final AlterarCategoriaService service = new AlterarCategoriaService(findCategoriaByIdPort,
                                                                                saveCategoriaPort);

    @Test
    void deveAlterarNomeDaCategoriaDeBebidaParaEletronico() {

        Long idCategoria = 1L;
        String novoNomeCategoria = "ELETRONICO";
        Categoria categoria = CategoriaCreator.umaCategoria("BEBIDAS");


        given(findCategoriaByIdPort.findCategoriaByIdPort(idCategoria)).willReturn(categoria);
        given(saveCategoriaPort.salvarCategoria(categoria)).willReturn(categoria);

        Categoria categoriaAtualizada = service.execute(idCategoria, novoNomeCategoria);

        then(findCategoriaByIdPort).should()
                                   .findCategoriaByIdPort(idCategoria);
        then(saveCategoriaPort).should()
                               .salvarCategoria(categoria);

        assertThat(categoriaAtualizada.getNome()).isEqualTo(novoNomeCategoria);

    }

    @Test
    void deveriaLancarResourceNotFoundExceptionQuandoTentarAlterarCategoriaComIdInvalido(){

        Long idCategoriaInvalido = 99999L;

        doThrow( new ResourceNotFoundException("Categoria não encontrada"))
                .when(findCategoriaByIdPort).findCategoriaByIdPort(idCategoriaInvalido);

        assertThatThrownBy(() -> {
            service.execute(idCategoriaInvalido, "any_nome");
        }).isInstanceOf(ResourceNotFoundException.class);

        then(findCategoriaByIdPort).should().findCategoriaByIdPort(idCategoriaInvalido);
        then(saveCategoriaPort).shouldHaveNoInteractions();

    }
}