package estudos.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private DadosPessoais dadosPessoais;

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNasc) {

        this.dadosPessoais = new DadosPessoais(nome, cpf, telefone, dataNasc);

    }

    @Override
    public String toString() {

        return "Cliente{" + "id=" + id + ", dadosPessoais=" + dadosPessoais + '}';
    }
}
