package estudos.ecommerce.carrinho.adapter.in.web;

import estudos.ecommerce.carrinho.adapter.in.web.request.CarrinhoRequest;
import estudos.ecommerce.carrinho.application.port.in.AdicionaProdutoNoCarrinhoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RequestMapping("/ecommerce-api/carrinho")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdicionaProdutoNoCarrinhoController {

   private final AdicionaProdutoNoCarrinhoUseCase adicionarProdutoNoCarrinhoUseCase;

   @PostMapping("/adicionar-produto")
    public ResponseEntity<Void> adicionaProdutoNoCarrinho(@RequestBody @Valid CarrinhoRequest request){

       adicionarProdutoNoCarrinhoUseCase.execute(request.getIdCliente(),
                                                 request.getIdProduto(),
                                                 request.getQuantidade());

       return ResponseEntity.status(HttpStatus.CREATED).build();
   }

}
