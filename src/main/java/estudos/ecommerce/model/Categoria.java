package estudos.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "categorias")
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true) private String nome;

    public Categoria(String nome) {

        this.nome = formataNomeCategoria(nome);
    }

    public void alteraNomeCategoria(String nome) {

        this.nome = formataNomeCategoria(nome);
    }

    private String formataNomeCategoria(String nome) {

        return nome.replaceAll(" ", "")
                   .toUpperCase();
    }
}
