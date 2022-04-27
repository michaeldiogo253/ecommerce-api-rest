package estudos.ecommerce.carrinho.adapter.in.web;

import estudos.ecommerce.carrinho.adapter.in.web.request.CarrinhoRequest;
import estudos.ecommerce.carrinho.adapter.in.web.response.CarrinhoResponse;
import estudos.ecommerce.carrinho.application.port.in.AdicionaProdutoNoCarrinhoUseCase;
import estudos.ecommerce.carrinho.domain.Carrinho;
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

@RequestMapping("/ecommerce-api/carrinho")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdicionaProdutoNoCarrinhoController {

    private final AdicionaProdutoNoCarrinhoUseCase adicionarProdutoNoCarrinhoUseCase;

    @PostMapping("/adicionar-produto")
    public ResponseEntity<CarrinhoResponse> adicionaProdutoNoCarrinho(@RequestBody @Valid CarrinhoRequest request,
                                                                      UriComponentsBuilder uriComponentsBuilder) {

        Carrinho carrinho = adicionarProdutoNoCarrinhoUseCase.execute(request.getIdCliente(),
                                                                      request.getIdProduto(),
                                                                      request.getQuantidade());

        URI uri = uriComponentsBuilder.path("ecommerce-api/categoria/{id}")
                                      .buildAndExpand(carrinho.getId())
                                      .toUri();

        return ResponseEntity.created(uri)
                             .body(CarrinhoResponse.from(carrinho));
    }

}
