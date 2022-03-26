package estudos.ecommerce.carrinho.domain;

import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.itemdocarrinho.domain.ItemDoCarrinho;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Carrinho {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @OneToOne private Cliente cliente;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL,
               orphanRemoval = true) private List<ItemDoCarrinho> carrinho = new ArrayList<>();

    private BigDecimal total = new BigDecimal(0);

    private Integer quantidadeTotalItensCarrinho = 0;

    public Carrinho(Cliente cliente) {

        this.cliente = cliente;
    }

    public void adicionaItemNoCarrinho(ItemDoCarrinho item, Integer quantidade) {

        carrinho.stream()
                .filter(itemDoCarrinho -> item.getProdutoId()
                                              .equals(itemDoCarrinho.getProdutoId()))
                .findFirst()
                .ifPresentOrElse(produto -> carrinho.get(carrinho.indexOf(produto))
                                                    .aumentaQuantidade(quantidade), () -> {
                    carrinho.add(item);
                });

        atualizaTotaisDoCarrinho();
    }

    public void removeItemDoCarrinho(ItemDoCarrinho item) {

        carrinho.removeIf(itemDoCarrinho -> itemDoCarrinho.equals(item));
    }

    public void atualizaTotaisDoCarrinho() {

        total = carrinho.stream()
                        .map(ItemDoCarrinho::getValorTotalDosItens)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.quantidadeTotalItensCarrinho = carrinho.stream()
                                                    .map(ItemDoCarrinho::getQuantidade)
                                                    .reduce(0, Integer::sum);

    }

}
