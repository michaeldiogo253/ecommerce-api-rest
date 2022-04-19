package estudos.ecommerce.categoria.adapter.in.web;

import estudos.ecommerce.categoria.adapter.in.web.request.CategoriaRequest;
import estudos.ecommerce.categoria.adapter.in.web.response.CategoriaResponse;
import estudos.ecommerce.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("ecommerce-api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;


    @Transactional
    @GetMapping("/listar-todas")
    public ResponseEntity<List<CategoriaResponse>> listarTodasCategorias() {
        return categoriaService.listarTodasCategorias();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarCategoriaPorId(
            @PathVariable Long id, @RequestBody @Valid CategoriaRequest request) {
        return categoriaService.alterarCategoria(id, request);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.deletarCategoria(id);
    }
}
