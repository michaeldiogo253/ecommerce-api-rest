package estudos.ecommerce.carrinho.application.service;

import estudos.ecommerce.carrinho.application.port.out.DeleteCarrinhoByIdPort;
import estudos.ecommerce.carrinho.application.port.out.FindCarrinhoByIdClientePort;
import estudos.ecommerce.carrinho.application.port.out.SaveCarrinhoPort;
import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.application.port.in.RemoveItemDoCarrinhoUseCase;
import estudos.ecommerce.itemdocarrinho.application.port.out.FindItemCarrinhoByIdCarrinhoAndIdProdutoPort;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class RemoveItemDoCarrinhoService implements RemoveItemDoCarrinhoUseCase {

    private final FindCarrinhoByIdClientePort findCarrinhoByIdClientePort;
    private final FindItemCarrinhoByIdCarrinhoAndIdProdutoPort findItemCarrinhoByIdCarrinhoAndIdProdutoPort;
    private final DeleteCarrinhoByIdPort deleteCarrinhoByIdPort;
    private final SaveCarrinhoPort saveCarrinhoPort;

    @Override
    public void execute(Long idCliente, Long idProduto) {
        Carrinho carrinho = findCarrinhoByIdClientePort.findCarrinhoByIdCliente(idCliente)
                                                       .orElseThrow(() -> new IllegalArgumentException(
                                                               "Carrinho do cliente não encontrado"));
       var itemDoCarrinho =
                findItemCarrinhoByIdCarrinhoAndIdProdutoPort.findItemCarrinhoByIdCarrinhoAndIdProduto(
                carrinho.getId(),
                idProduto).orElseThrow(() -> new IllegalArgumentException(
                        "Item do Carrinho não encontrado"));

        carrinho.removeItemDoCarrinho(itemDoCarrinho);
        carrinho.atualizaTotaisDoCarrinho();

        if(carrinho.carrinhoIsVazio()){
            deleteCarrinhoByIdPort.deleteCarrinhoPorId(carrinho.getId());
            return;
        }

        saveCarrinhoPort.salvarCarrinho(carrinho);
    }
}
