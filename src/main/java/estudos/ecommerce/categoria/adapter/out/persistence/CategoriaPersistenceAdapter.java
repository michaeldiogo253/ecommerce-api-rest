package estudos.ecommerce.categoria.adapter.out.persistence;

import estudos.ecommerce.categoria.application.port.out.*;
import estudos.ecommerce.categoria.domain.Categoria;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaPersistenceAdapter implements FindCategoriaByNomePort,
                                                    SaveCategoriaPort,
                                                    FindCategoriaByIdPort,
                                                    DeletarCategoriaByIdPort,
                                                    FindAllCategoriasPort {

    private final CategoriaRepository categoriaRepository;

    @Override
    public Categoria findCategoriaPorNome(String nomeCategoria) {

        return categoriaRepository.findCategoriaByNome(nomeCategoria)
                                  .orElseThrow(()-> new ResourceNotFoundException("Categoria não encontrada"));
    }

    @Override
    public Categoria salvarCategoria(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria findCategoriaByIdPort(Long idCategoria) {

        return categoriaRepository.findById(idCategoria)
                                  .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
    }

    @Override
    public Class<? extends Throwable> deletarCategoriaPorId(Long idCategoria) {

        if( categoriaRepository.findById(idCategoria).isEmpty()){
            throw new ResourceNotFoundException("Categoria não encontrada");
        }

        categoriaRepository.deleteById(idCategoria);

        return null;
    }

    @Override
    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAll();
    }
}
