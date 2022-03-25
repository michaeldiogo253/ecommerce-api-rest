package estudos.ecommerce.controller;

import estudos.ecommerce.carrinho.adapter.in.web.request.CarrinhoRequest;
import estudos.ecommerce.service.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/listar-itens-do-carrinho/{idCliente}")
    public ResponseEntity<Void> listarItensDoCarrinho(@PathVariable Long idCliente){
        carrinhoService.listarItensDoCarrinho(idCliente);
        return ResponseEntity.ok().build();
    }

}
