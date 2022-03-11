package estudos.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class ItemDoCarrinho {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @ManyToOne private Produto produto;
    private Integer quantidade = 0;
    private BigDecimal valorTotalDosItens = BigDecimal.ZERO;

    @ManyToOne private Carrinho carrinho;

    public ItemDoCarrinho(Produto produto, Integer quantidade, Carrinho carrinho) {

        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotalDosItens = new BigDecimal(String.valueOf(produto.getPreco()
                                                                       .multiply(BigDecimal.valueOf(quantidade))));
        this.carrinho = carrinho;
    }

    public BigDecimal getPrecoProduto() {

        return this.produto.getPreco();
    }

    public Long getProdutoId() {

        return this.produto.getId();
    }

    public void aumentaQuantidade(Integer quantidade) {

        this.quantidade += quantidade;
        this.valorTotalDosItens = new BigDecimal(String.valueOf(produto.getPreco()
                                                                       .multiply(BigDecimal.valueOf(this.quantidade))));
    }

    public void subtraiQuantidade(Integer quantidade) {

        this.quantidade -= quantidade;
        this.valorTotalDosItens = new BigDecimal(String.valueOf(produto.getPreco()
                                                                       .multiply(BigDecimal.valueOf(this.quantidade))));
    }

}
