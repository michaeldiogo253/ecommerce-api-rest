package estudos.ecommerce.categoria.adapter.in.web;

import estudos.ecommerce.categoria.adapter.in.web.response.CategoriaResponse;
import estudos.ecommerce.categoria.application.port.out.FindCategoriaByIdPort;
import estudos.ecommerce.categoria.domain.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BuscarCategoriaPorIdController {

    private final FindCategoriaByIdPort findCategoriaByIdPort;

    @GetMapping("/ecommerce-api/categoria/{idCategoria}")
    public ResponseEntity<CategoriaResponse> buscarCategoriaPorId(@PathVariable Long idCategoria) {

        Categoria categoriaBuscada = findCategoriaByIdPort.findCategoriaByIdPort(idCategoria);

        return ResponseEntity.ok()
                             .body(CategoriaResponse.from(categoriaBuscada));
    }

}
