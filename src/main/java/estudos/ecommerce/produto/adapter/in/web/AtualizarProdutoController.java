package estudos.ecommerce.produto.adapter.in.web;

import estudos.ecommerce.produto.adapter.in.web.request.AtualizaProdutoRequest;
import estudos.ecommerce.produto.adapter.in.web.response.ProdutoResponse;
import estudos.ecommerce.produto.application.service.AtualizarProdutoService;
import estudos.ecommerce.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/ecommerce-api/produto")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtualizarProdutoController {

    private final AtualizarProdutoService service;

    @PutMapping(path = "/atualizar")
    public ResponseEntity<ProdutoResponse> execute(@RequestBody @Valid AtualizaProdutoRequest request) {

        Produto produtoAtualizado = service.execute(request.getIdProduto(),
                                                    request.getNome(),
                                                    request.getDescricao(),
                                                    request.getPreco());

        return ResponseEntity.ok()
                             .body(ProdutoResponse.from(produtoAtualizado));
    }

}