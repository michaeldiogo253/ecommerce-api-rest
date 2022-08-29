package estudos.ecommerce.usuario.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Perfil implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String nome;

    public Perfil(String nome) {

        this.nome = nome;
    }

    @Override
    public String getAuthority() {

        return nome;
    }
}
