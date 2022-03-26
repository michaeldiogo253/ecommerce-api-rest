package estudos.ecommerce.carrinho.adapter.out.persistence;

import estudos.ecommerce.carrinho.application.port.out.FindCarrinhoByIdClientePort;
import estudos.ecommerce.carrinho.application.port.out.SaveCarrinhoPort;
import estudos.ecommerce.carrinho.domain.Carrinho;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarrinhoPersistenceAdapter implements FindCarrinhoByIdClientePort, SaveCarrinhoPort {

    private final CarrinhoRepository carrinhoRepository;

    @Override
    public Optional<Carrinho> findCarrinhoByIdCliente(Long idCliente) {

        return carrinhoRepository.findByIdCliente(idCliente);
    }

    @Override
    public Carrinho salvarCarrinho(Carrinho carrinho) {

        return carrinhoRepository.save(carrinho);
    }
}
