package estudos.ecommerce.categoria.adapter.out.persistence;

import estudos.ecommerce.categoria.application.port.out.FindCategoriaByNomePort;
import estudos.ecommerce.categoria.domain.Categoria;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaPersistenceAdapter implements FindCategoriaByNomePort {

    private final CategoriaRepository categoriaRepository;

    @Override
    public Categoria findCategoriaPorNome(String nomeCategoria) {

        return categoriaRepository.findCategoriaByNome(nomeCategoria)
                                  .orElseThrow(()-> new ResourceNotFoundException("Categoria não encontrada"));
    }
}
