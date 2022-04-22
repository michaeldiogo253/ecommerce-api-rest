package estudos.ecommerce.categoria.application.port.in;

import estudos.ecommerce.categoria.domain.Categoria;

public interface AlterarCategoriaUseCase {

    Categoria execute(Long idCategoria, String novoNomeCategoria);
}
