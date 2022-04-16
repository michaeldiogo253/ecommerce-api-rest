package estudos.ecommerce.categoria.application.port.in;

import estudos.ecommerce.categoria.adapter.in.web.request.CategoriaRequest;
import estudos.ecommerce.categoria.domain.Categoria;

public interface SaveCategoriaUseCase {

    Categoria execute(CategoriaRequest request);
}
