package estudos.ecommerce.service;

import estudos.ecommerce.controller.request.CarrinhoRequest;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.carrinho.domain.ItemDoCarrinho;
import estudos.ecommerce.repository.CarrinhoRepository;
import estudos.ecommerce.cliente.adapter.out.persistence.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemDoCarrinhoService itemDoCarrinhoService;

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

    private Carrinho adicionaProdutoEmCarrinhoExistente(Carrinho carrinhoBuscado,
                                                        ItemDoCarrinho itemDoCarrinho,
                                                        Integer quantidade) {

        carrinhoBuscado.adicionaItemNoCarrinho(itemDoCarrinho, quantidade);
        carrinhoRepository.save(carrinhoBuscado);
        return carrinhoBuscado;
    }

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

    public void removeProdutoDoCarrinho(CarrinhoRequest request) {

        Carrinho carrinhoBuscado = carrinhoRepository.findByIdCliente(request.getIdCliente())
                                                     .orElseThrow(() -> new ResourceNotFoundException(
                                                             "Carrinho não encontrado"));

        ItemDoCarrinho itemDoCarrinho = itemDoCarrinhoService.removeProdutoCarrinho(carrinhoBuscado,
                                                                                    request.getIdProduto(),
                                                                                    request.getQuantidade());
        if (verificaSePrecisaDeletarItemCarrinho(itemDoCarrinho)) {
            carrinhoBuscado.removeItemDoCarrinho(itemDoCarrinho);
        }
        carrinhoBuscado.atualizaTotaisDoCarrinho();
        carrinhoRepository.save(carrinhoBuscado);
    }

    public boolean verificaSePrecisaDeletarItemCarrinho(ItemDoCarrinho itemDoCarrinho) {

        return itemDoCarrinho.getQuantidade() == 0;
    }

    public void listarItensDoCarrinho(Long idCarrinho) {

        Optional<Carrinho> carrinho = carrinhoRepository.findById(idCarrinho);

       carrinho.get().getCarrinho().get(0);

    }
}
