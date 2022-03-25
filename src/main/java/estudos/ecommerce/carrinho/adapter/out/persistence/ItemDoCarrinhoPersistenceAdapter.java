package estudos.ecommerce.carrinho.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemDoCarrinhoPersistenceAdapter {

    private final ItemDoCarrinhoRepository itemDoCarrinhoRepository;
}
