package estudos.ecommerce.model;

import estudos.ecommerce.controller.request.ProdutoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "produtos")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataCadastro = LocalDate.now();

    @ManyToOne private Categoria categoria;

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {

        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public static Produto from(ProdutoRequest request, Categoria categoria) {

        return new Produto(request.getNome(), request.getDescricao(), request.getPreco(), categoria);
    }
}
