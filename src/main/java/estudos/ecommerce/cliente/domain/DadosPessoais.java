package estudos.ecommerce.cliente.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DadosPessoais {

    @Column(unique = true) private String nome;
    @Column(unique = true) private String cpf;
    private String telefone;
    private LocalDate dataNasc;
}
