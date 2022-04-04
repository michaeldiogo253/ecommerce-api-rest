package estudos.ecommerce.carrinho.adapter.out.persistence;

import estudos.ecommerce.carrinho.application.port.out.DeleteCarrinhoByIdPort;
import estudos.ecommerce.carrinho.application.port.out.FindCarrinhoByIdClientePort;
import estudos.ecommerce.carrinho.application.port.out.SaveCarrinhoPort;
import estudos.ecommerce.carrinho.domain.Carrinho;
import estudos.ecommerce.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class CarrinhoPersistenceAdapter implements FindCarrinhoByIdClientePort,
                                                   SaveCarrinhoPort,
                                                   DeleteCarrinhoByIdPort{

    private final CarrinhoRepository carrinhoRepository;

    @Override
    public Optional<Carrinho> findCarrinhoByIdCliente(Long idCliente) {

        return carrinhoRepository.findByIdCliente(idCliente);
    }

    @Override
    public Carrinho salvarCarrinho(Carrinho carrinho) {

        return carrinhoRepository.save(carrinho);
    }

    @Override
    public void deleteCarrinhoPorId(Long idCarrinho) {
        if(!carrinhoRepository.existsById(idCarrinho)){
            throw new ResourceNotFoundException("Carrinho não encontrado");
        }

        carrinhoRepository.deleteById(idCarrinho);
    }
}
