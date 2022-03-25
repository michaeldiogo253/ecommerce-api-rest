package estudos.ecommerce.carrinho.application.service;

import estudos.ecommerce.carrinho.application.port.in.AdicionarProdutoNoCarrinhoUseCase;
import estudos.ecommerce.carrinho.application.port.out.FindCarrinhoByIdClientePort;
import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.carrinho.domain.ItemDoCarrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdicionaProdutoNoCarrinhoService implements AdicionarProdutoNoCarrinhoUseCase {

    private final FindCarrinhoByIdClientePort findCarrinhoByIdClientePort;

    @Override
    public void execute(Long idCliente, Long idProduto, Integer quantidade) {
        Optional<Carrinho> carrinhoBuscado = findCarrinhoByIdClientePort.findCarrinhoByIdCliente(idCliente);

        if (carrinhoBuscado.isPresent()) {
            ItemDoCarrinho itemDoCarrinho = itemDoCarrinhoService.buscaItemCarrinho(carrinhoBuscado.get(),
                                                                                    idProduto,
                                                                                    quantidade);

            return adicionaProdutoEmCarrinhoExistente(carrinhoBuscado.get(), itemDoCarrinho, request.getQuantidade());
        }

        return criaNovoCarrinhoEAdicionaProduto(request);
    }
}
