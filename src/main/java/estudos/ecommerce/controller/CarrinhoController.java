package estudos.ecommerce.controller;

import estudos.ecommerce.controller.request.CarrinhoRequest;
import estudos.ecommerce.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @PostMapping("/adicionar-produto")
    public ResponseEntity<Void> adicionarProdutoNoCarrinho(@RequestBody @Valid CarrinhoRequest request) {

        carrinhoService.adicionaProdutoNoCarrinho(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .build();
    }

    @PostMapping("/remover-produto")
    public ResponseEntity<Void> removerProdutoDoCarrinho(@RequestBody @Valid CarrinhoRequest request){
        carrinhoService.removeProdutoDoCarrinho(request);

        return ResponseEntity.ok().build();
    }

}
