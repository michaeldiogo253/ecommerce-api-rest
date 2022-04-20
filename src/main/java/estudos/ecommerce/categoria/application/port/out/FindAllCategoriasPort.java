package estudos.ecommerce.categoria.application.port.out;

import estudos.ecommerce.categoria.domain.Categoria;

import java.util.List;

public interface FindAllCategoriasPort {

    List<Categoria> findAllCategorias();
}
