package estudos.ecommerce.categoria.application.service;

import estudos.ecommerce.categoria.application.port.in.DeletarCategoriaByIdUseCase;
import estudos.ecommerce.categoria.application.port.out.DeletarCategoriaByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeletarCategoriaByIdService implements DeletarCategoriaByIdUseCase {

    private final DeletarCategoriaByIdPort deletarCategoriaByIdPort;

    @Override
    public void execute(Long idCategoria) {
        deletarCategoriaByIdPort.deletarCategoriaPorId(idCategoria);
    }
}
