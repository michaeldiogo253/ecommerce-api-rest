package estudos.ecommerce.categoria.application.service;

import estudos.ecommerce.categoria.application.port.in.AlterarCategoriaUseCase;
import estudos.ecommerce.categoria.application.port.out.FindCategoriaByIdPort;
import estudos.ecommerce.categoria.application.port.out.SaveCategoriaPort;
import estudos.ecommerce.categoria.domain.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlterarCategoriaService implements AlterarCategoriaUseCase {

    private final FindCategoriaByIdPort findCategoriaByIdPort;
    private final SaveCategoriaPort saveCategoriaPort;

    @Override
    public void execute(Long idCategoria, String novoNomeCategoria) {

        Categoria categoria = findCategoriaByIdPort.findCategoriaByIdPort(idCategoria);
        categoria.alteraNomeCategoria(novoNomeCategoria);
        saveCategoriaPort.salvarCategoria(categoria);
    }
}
