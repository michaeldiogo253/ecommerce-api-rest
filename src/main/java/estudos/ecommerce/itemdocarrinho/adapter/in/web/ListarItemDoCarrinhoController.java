package estudos.ecommerce.itemdocarrinho.adapter.in.web;

import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.adapter.in.web.response.ItemDoCarrinhoResponse;
import estudos.ecommerce.itemdocarrinho.adapter.in.web.response.ListarItensDoCarrinhoResponse;
import estudos.ecommerce.itemdocarrinho.application.port.out.FindAllItensCarrinhoByIdCarrinhoPort;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/carrinho")
public class ListarItemDoCarrinhoController {

    private final FindAllItensCarrinhoByIdCarrinhoPort findAllItensCarrinhoByIdCarrinhoPort;

    @GetMapping("/listar-itens/{idCarrinho}")
    public ResponseEntity<ListarItensDoCarrinhoResponse> listarItemsDoCarrinho(@PathVariable Long idCarrinho){

        List<ItemDoCarrinho> itensDoCarrinhoByIdCarrinho =
                findAllItensCarrinhoByIdCarrinhoPort.findItensDoCarrinhoByIdCarrinho(
                idCarrinho);

        ListarItensDoCarrinhoResponse itensDoCarrinhoResponse =
                new ListarItensDoCarrinhoResponse(itensDoCarrinhoByIdCarrinho.stream().map(ItemDoCarrinho::getIdCarrinho).findFirst().orElse(0L),
                                                  itensDoCarrinhoByIdCarrinho.stream().map(ItemDoCarrinho::getNomeCliente).findFirst().orElse(""),
                                                  itensDoCarrinhoByIdCarrinho.stream().map(ItemDoCarrinho::getValorTotalDoCarrinho).findFirst().orElse(BigDecimal.ZERO),
                                                  itensDoCarrinhoByIdCarrinho.stream().map(ItemDoCarrinho::getCarrinho).findFirst()
                                                                             .map(Carrinho::getQuantidadeTotalItensCarrinho).stream().findFirst().orElse(0),
                                                  ItemDoCarrinhoResponse.of(itensDoCarrinhoByIdCarrinho));

        return ResponseEntity.ok().body(itensDoCarrinhoResponse);
    }
}
