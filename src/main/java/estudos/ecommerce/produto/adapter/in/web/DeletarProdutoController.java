package estudos.ecommerce.produto.adapter.in.web;

import estudos.ecommerce.produto.application.service.DeletarProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/produto")
public class DeletarProdutoController {

    private final DeletarProdutoService deletarProdutoService;

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletaProdutoPorId(@PathVariable Long idProduto) {

        deletarProdutoService.execute(idProduto);
        return ResponseEntity.ok().build();
    }

}
