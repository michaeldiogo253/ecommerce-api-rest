package estudos.ecommerce.carrinho.adapter.in.web;

import estudos.ecommerce.carrinho.adapter.in.web.request.CarrinhoRequest;
import estudos.ecommerce.carrinho.application.port.in.RemoveProdutoDoCarrinhoUseCase;
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
public class RemoveProdutoDoCarrinhoPorQuantidadeController {
    private final RemoveProdutoDoCarrinhoUseCase removeProdutoDoCarrinhoUseCase;

    @PostMapping("/remover-produto")
    public ResponseEntity<Void> removerProdutoDoCarrinho(@RequestBody @Valid CarrinhoRequest request){

        removeProdutoDoCarrinhoUseCase.execute(request.getIdCliente(), request.getIdProduto(), request.getQuantidade());

        return ResponseEntity.ok().build();
    }
}
