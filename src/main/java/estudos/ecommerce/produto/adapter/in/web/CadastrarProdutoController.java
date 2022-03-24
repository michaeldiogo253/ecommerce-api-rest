package estudos.ecommerce.produto.adapter.in.web;

import estudos.ecommerce.produto.adapter.in.web.request.ProdutoRequest;
import estudos.ecommerce.produto.adapter.in.web.response.ProdutoResponse;
import estudos.ecommerce.produto.application.port.in.CadastrarProdutoUseCase;
import estudos.ecommerce.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/produto")
public class CadastrarProdutoController {

    private final CadastrarProdutoUseCase cadastrarProdutoUseCase;

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody @Valid ProdutoRequest request,
                                                            UriComponentsBuilder uriBuilder) {

        Produto produtoCriado = cadastrarProdutoUseCase.execute(request.toModel(request), request.getCategoria());
        URI uri = uriBuilder.path("ecommerce-api/produto/{id}")
                            .buildAndExpand(produtoCriado.getId())
                            .toUri();

        return ResponseEntity.created(uri)
                             .body(ProdutoResponse.from(produtoCriado));
    }

}
