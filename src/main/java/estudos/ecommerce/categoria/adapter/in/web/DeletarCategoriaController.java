package estudos.ecommerce.categoria.adapter.in.web;

import estudos.ecommerce.categoria.application.port.in.DeletarCategoriaByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeletarCategoriaController {

    private final DeletarCategoriaByIdUseCase deletarCategoriaByIdUseCase;

    @DeleteMapping("/ecommerce-api/categoria/deletar/{idCategoria}")
    public ResponseEntity<Void> deletarCategoriaPorId(@PathVariable Long idCategoria) {

        deletarCategoriaByIdUseCase.execute(idCategoria);

        return ResponseEntity.ok().build();

    }
}
