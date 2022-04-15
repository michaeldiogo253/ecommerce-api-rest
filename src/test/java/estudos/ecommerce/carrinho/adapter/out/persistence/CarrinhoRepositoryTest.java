package estudos.ecommerce.carrinho.adapter.out.persistence;

import estudos.ecommerce.carrinho.domain.Carrinho;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class CarrinhoRepositoryTest {


    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Test
    void deveriaListarCarrinhoPeloIdDoCliente(){
        Long idCliente = 1L;

        Optional<Carrinho> carrinho = carrinhoRepository.findByIdCliente(idCliente);

        assertThat(carrinho).isPresent();
    }

    @Test
    void naoDeveriaListarCarrinhoPeloIdDoClienteQueNaoExiste(){
        Long idCliente = 100000000L;

        Optional<Carrinho> carrinho = carrinhoRepository.findByIdCliente(idCliente);

        assertThat(carrinho).isEmpty();
    }
}