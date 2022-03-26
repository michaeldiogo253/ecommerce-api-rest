package estudos.ecommerce.carrinho.application.service;

import estudos.ecommerce.carrinho.application.port.in.AdicionaProdutoEmUmCarrinhoExistenteUseCase;
import estudos.ecommerce.carrinho.application.port.out.SaveCarrinhoPort;
import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdicionaProdutoEmUmCarrinhoExistenteService implements AdicionaProdutoEmUmCarrinhoExistenteUseCase {

    private final SaveCarrinhoPort saveCarrinhoPort;
    @Override
    public Carrinho adicionaProdutoEmCarrinhoExistente(Carrinho carrinhoBuscado,
                                                       ItemDoCarrinho itemDoCarrinho,
                                                       Integer quantidade) {

        carrinhoBuscado.adicionaItemNoCarrinho(itemDoCarrinho, quantidade);
        saveCarrinhoPort.salvarCarrinho(carrinhoBuscado);
        return carrinhoBuscado;
    }
}
