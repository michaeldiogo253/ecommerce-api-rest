package estudos.ecommerce.pedido.domain;

import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.cliente.domain.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    private LocalDate dataFechamento = LocalDate.now();
    @Embedded private Endereco enderecoEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItensPedido> itens = new ArrayList<>();


    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItensPedido item) {
        item.setPedido(this);
        this.itens.add(item);
    }
}
