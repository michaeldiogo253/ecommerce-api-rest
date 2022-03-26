package estudos.ecommerce.carrinho.application.service;

import estudos.ecommerce.carrinho.application.port.in.AdicionaProdutoEmUmNovoCarrinhoUseCase;
import estudos.ecommerce.carrinho.application.port.in.AdicionaProdutoNoCarrinhoUseCase;
import estudos.ecommerce.carrinho.application.port.out.FindCarrinhoByIdClientePort;
import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.application.port.in.BuscaItemDoCarrinhoUseCase;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdicionaProdutoNoCarrinhoService implements AdicionaProdutoNoCarrinhoUseCase {

    private final FindCarrinhoByIdClientePort findCarrinhoByIdClientePort;
    private final BuscaItemDoCarrinhoUseCase buscaItemDoCarrinhoUseCase;
    private final AdicionaProdutoEmUmCarrinhoExistenteService adicionaProdutoEmUmCarrinhoExistenteService;
    private final AdicionaProdutoEmUmNovoCarrinhoUseCase adicionaProdutoEmUmNovoCarrinhoUseCase;

    @Override
    public Carrinho execute(Long idCliente, Long idProduto, Integer quantidade) {

        Optional<Carrinho> carrinhoBuscado = findCarrinhoByIdClientePort.findCarrinhoByIdCliente(idCliente);

        if (carrinhoBuscado.isPresent()) {


            ItemDoCarrinho itemDoCarrinho = buscaItemDoCarrinhoUseCase.execute(carrinhoBuscado.get(), idProduto, quantidade);

            return adicionaProdutoEmUmCarrinhoExistenteService.adicionaProdutoEmCarrinhoExistente(carrinhoBuscado.get(), itemDoCarrinho, quantidade);
        }

        return adicionaProdutoEmUmNovoCarrinhoUseCase.execute(idCliente, idProduto, quantidade);
    }
}
