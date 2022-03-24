package estudos.ecommerce.produto.adapter.in.web;

import estudos.ecommerce.produto.adapter.in.web.response.ProdutoResponse;
import estudos.ecommerce.produto.application.port.out.FindProdutoByIdPort;
import estudos.ecommerce.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/produto")
public class BuscarProdutoPorIdController {

    private final FindProdutoByIdPort findProdutoByIdPort;

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponse> buscarProdutoPorId(@PathVariable Long idProduto) {

        Produto produtoBuscado = findProdutoByIdPort.findProdutoById(idProduto);

        return ResponseEntity.ok()
                             .body(ProdutoResponse.from(produtoBuscado));
    }
}
