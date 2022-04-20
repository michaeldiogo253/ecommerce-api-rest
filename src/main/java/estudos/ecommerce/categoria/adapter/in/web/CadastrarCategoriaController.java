package estudos.ecommerce.categoria.adapter.in.web;

import estudos.ecommerce.categoria.adapter.in.web.request.CategoriaRequest;
import estudos.ecommerce.categoria.adapter.in.web.response.CategoriaResponse;
import estudos.ecommerce.categoria.application.port.in.SaveCategoriaUseCase;
import estudos.ecommerce.categoria.domain.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CadastrarCategoriaController {

    private final SaveCategoriaUseCase saveCategoriaUseCase;

    @PostMapping("/ecommerce-api/categoria/cadastrar")
    public ResponseEntity<CategoriaResponse> cadastrarCategoria(@RequestBody @Valid CategoriaRequest request,
                                                                UriComponentsBuilder uriBuilder) {

        Categoria categoria = saveCategoriaUseCase.execute(request);

        URI uri = uriBuilder.path("ecommerce-api/categoria/{id}")
                            .buildAndExpand(categoria.getId())
                            .toUri();

        return ResponseEntity.created(uri)
                             .body(CategoriaResponse.from(categoria));

    }
}
