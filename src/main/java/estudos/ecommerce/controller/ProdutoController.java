package estudos.ecommerce.controller;

import estudos.ecommerce.controller.request.ProdutoRequest;
import estudos.ecommerce.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastraProduto(@RequestBody @Valid ProdutoRequest request) {

        produtoService.cadastrarProduto(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
