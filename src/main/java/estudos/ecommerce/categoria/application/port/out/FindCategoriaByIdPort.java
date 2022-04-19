package estudos.ecommerce.categoria.application.port.out;

import estudos.ecommerce.categoria.domain.Categoria;

public interface FindCategoriaByIdPort {

    Categoria findCategoriaByIdPort(Long idCategoria);
}
