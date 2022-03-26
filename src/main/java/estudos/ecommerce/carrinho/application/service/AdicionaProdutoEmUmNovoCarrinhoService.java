package estudos.ecommerce.carrinho.application.service;

import estudos.ecommerce.carrinho.application.port.in.AdicionaProdutoEmUmNovoCarrinhoUseCase;
import estudos.ecommerce.carrinho.application.port.in.AdicionaProdutoNoCarrinhoUseCase;
import estudos.ecommerce.carrinho.application.port.out.SaveCarrinhoPort;
import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.cliente.application.port.out.FindClienteByIdPort;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.itemdocarrinho.application.port.in.CriaNovoItemCarrinhoUseCase;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdicionaProdutoEmUmNovoCarrinhoService implements AdicionaProdutoEmUmNovoCarrinhoUseCase {

    private final FindClienteByIdPort findClienteByIdPort;
    private final CriaNovoItemCarrinhoUseCase criaNovoItemCarrinhoUseCase;
    private final SaveCarrinhoPort saveCarrinhoPort;

    @Override
    public Carrinho execute(Long idCliente, Long idProduto, Integer quantidade) {

        Cliente cliente = findClienteByIdPort.buscaClientePorId(idCliente);


        Carrinho novoCarrinho = new Carrinho(cliente);
        ItemDoCarrinho itemDoCarrinho = criaNovoItemCarrinhoUseCase.criaNovoItemDoCarrinho(idProduto,
                                                                                     quantidade,
                                                                                     novoCarrinho);
        novoCarrinho.adicionaItemNoCarrinho(itemDoCarrinho, quantidade);
        saveCarrinhoPort.salvarCarrinho(novoCarrinho);
        return novoCarrinho;
    }
}
