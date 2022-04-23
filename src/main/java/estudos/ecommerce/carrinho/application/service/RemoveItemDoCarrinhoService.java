package estudos.ecommerce.carrinho.application.service;

import estudos.ecommerce.itemdocarrinho.application.port.in.RemoveItemDoCarrinhoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class RemoveItemDoCarrinhoService implements RemoveItemDoCarrinhoUseCase {

    @Override
    public void execute(Long idCliente, Long idProduto) {
        //todo implementar logica de remover item do carrinho
    }
}
