package estudos.ecommerce.categoria.adapter.in.web;

import estudos.ecommerce.categoria.adapter.in.web.response.CategoriaResponse;
import estudos.ecommerce.categoria.application.port.out.FindAllCategoriasPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ListarTodasAsCategoriasController {

    private final FindAllCategoriasPort findAllCategoriasPort;

    @GetMapping("/ecommerce-api/categoria/listar-todas")
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){

        List<CategoriaResponse> categoriaResponses =
                CategoriaResponse.from(findAllCategoriasPort.findAllCategorias());

        return ResponseEntity.ok().body(categoriaResponses);
    }
}
