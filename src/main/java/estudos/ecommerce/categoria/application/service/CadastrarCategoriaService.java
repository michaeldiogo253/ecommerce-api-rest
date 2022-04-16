package estudos.ecommerce.categoria.application.service;

import estudos.ecommerce.categoria.adapter.in.web.request.CategoriaRequest;
import estudos.ecommerce.categoria.application.port.in.SaveCategoriaUseCase;
import estudos.ecommerce.categoria.application.port.out.SaveCategoriaPort;
import estudos.ecommerce.categoria.domain.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CadastrarCategoriaService implements SaveCategoriaUseCase {

    private final SaveCategoriaPort saveCategoriaPort;

    @Override
    public Categoria execute(CategoriaRequest request) {

        Categoria categoria = request.toModel(request);

        return saveCategoriaPort.salvarCategoria(categoria);
    }
}
