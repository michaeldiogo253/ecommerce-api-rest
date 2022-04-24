package estudos.ecommerce.carrinho.adapter.in.web;

import estudos.ecommerce.carrinho.adapter.in.web.request.RemoverProdutoDoCarrinhoRequest;
import estudos.ecommerce.itemdocarrinho.application.port.in.RemoveItemDoCarrinhoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/ecommerce-api/carrinho")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RemoveItemDoCarrinhoController {

    private final RemoveItemDoCarrinhoUseCase useCase;

    @PostMapping("/remover-item-do-carrinho")
    public ResponseEntity<Void> removeProdutoDoCarrinho(@RequestBody @Valid RemoverProdutoDoCarrinhoRequest request) {

        useCase.execute(request.getIdCliente(), request.getIdProduto());

        return ResponseEntity.ok().build();
    }
}
