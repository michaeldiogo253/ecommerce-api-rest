package estudos.ecommerce.service;

import estudos.ecommerce.controller.request.CarrinhoRequest;
import estudos.ecommerce.exception.ResourceNotFoundException;
import estudos.ecommerce.model.Carrinho;
import estudos.ecommerce.model.Cliente;
import estudos.ecommerce.model.ItemDoCarrinho;
import estudos.ecommerce.repository.CarrinhoRepository;
import estudos.ecommerce.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemDoCarrinhoService itemDoCarrinhoService;

    @Transactional
    public Carrinho adicionaProdutoNoCarrinho(CarrinhoRequest request) {

        Optional<Carrinho> carrinhoBuscado = carrinhoRepository.findByIdCliente(request.getIdCliente());

        if (carrinhoBuscado.isPresent()) {
            ItemDoCarrinho itemDoCarrinho = itemDoCarrinhoService.buscaItemCarrinho(carrinhoBuscado.get(),
                                                                                    request.getIdProduto(),
                                                                                    request.getQuantidade());

            return adicionaProdutoEmCarrinhoExistente(carrinhoBuscado.get(), itemDoCarrinho, request.getQuantidade());
        }

        return criaNovoCarrinhoEAdicionaProduto(request);
    }

    @Transactional
    private Carrinho adicionaProdutoEmCarrinhoExistente(Carrinho carrinhoBuscado,
                                                        ItemDoCarrinho itemDoCarrinho,
                                                        Integer quantidade) {

        carrinhoBuscado.adicionaItemNoCarrinho(itemDoCarrinho, quantidade);
        carrinhoRepository.save(carrinhoBuscado);
        return carrinhoBuscado;
    }

    @Transactional
    private Carrinho criaNovoCarrinhoEAdicionaProduto(CarrinhoRequest request) {

        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                                           .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Carrinho novoCarrinho = new Carrinho(cliente);
        ItemDoCarrinho itemDoCarrinho = itemDoCarrinhoService.criaNovoItemDoCarrinho(request.getIdProduto(),
                                                                                     request.getQuantidade(),
                                                                                     novoCarrinho);
        novoCarrinho.adicionaItemNoCarrinho(itemDoCarrinho, request.getQuantidade());
        carrinhoRepository.save(novoCarrinho);
        return novoCarrinho;
    }

    @Transactional
    public void removeProdutoDoCarrinho(CarrinhoRequest request) {

        Carrinho carrinhoBuscado = carrinhoRepository.findByIdCliente(request.getIdCliente())
                                                     .orElseThrow(() -> new ResourceNotFoundException(
                                                             "Carrinho não encontrado"));

        itemDoCarrinhoService.removeProdutoCarrinho(carrinhoBuscado, request.getIdProduto(), request.getQuantidade());
        carrinhoBuscado.atualizaTotaisDoCarrinho();
        carrinhoRepository.save(carrinhoBuscado);

    }

}
