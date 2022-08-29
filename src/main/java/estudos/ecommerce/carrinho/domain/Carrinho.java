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

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDoCarrinho> itemDoCarrinhos = new ArrayList<>();

    private BigDecimal total = new BigDecimal(0);

    private Integer quantidadeTotalItensCarrinho = 0;

    public Carrinho(Cliente cliente) {

        this.cliente = cliente;
    }

    public void adicionaItemNoCarrinho(ItemDoCarrinho item, Integer quantidade) {

        itemDoCarrinhos.stream()
                       .filter(itemDoCarrinho -> item.getProdutoId()
                                              .equals(itemDoCarrinho.getProdutoId()))
                       .findFirst()
                       .ifPresentOrElse(produto -> itemDoCarrinhos.get(itemDoCarrinhos.indexOf(produto))
                                                                  .aumentaQuantidade(quantidade), () -> {
                    itemDoCarrinhos.add(item);
                });

        atualizaTotaisDoCarrinho();
    }

    public void removeItemDoCarrinho(ItemDoCarrinho item) {

        itemDoCarrinhos.removeIf(itemDoCarrinho -> itemDoCarrinho.equals(item));
    }

    public void atualizaTotaisDoCarrinho() {

        total = itemDoCarrinhos.stream()
                               .map(ItemDoCarrinho::getValorTotalDosItens)
                               .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.quantidadeTotalItensCarrinho = itemDoCarrinhos.stream()
                                                           .map(ItemDoCarrinho::getQuantidade)
                                                           .reduce(0, Integer::sum);

    }

    public boolean carrinhoIsVazio(){
        return itemDoCarrinhos.isEmpty();
    }

    public String getNomeCliente(){
        return this.cliente.getNome();
    }

    public Integer getQuantidadeDeItensDoCarrinho(){
        return this.itemDoCarrinhos.size();
    }
}
