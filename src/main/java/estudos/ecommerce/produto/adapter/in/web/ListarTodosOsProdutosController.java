package estudos.ecommerce.produto.adapter.in.web;

import estudos.ecommerce.produto.adapter.in.web.response.ProdutoResponse;
import estudos.ecommerce.produto.application.port.out.FindAllProdutoPort;
import estudos.ecommerce.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/produto")
public class ListarTodosOsProdutosController {

    private final FindAllProdutoPort findAllProdutoPort;

    @GetMapping("/listar-todos")
    public ResponseEntity<List<ProdutoResponse>> listarTodosOsProdutos(){

        List<Produto> produtos = findAllProdutoPort.listarTodosOsProdutos();

        return ResponseEntity.ok().body(ProdutoResponse.from(produtos));
    }

}
