package estudos.ecommerce.administrador.domain;

import estudos.ecommerce.cliente.domain.DadosPessoais;
import estudos.ecommerce.usuario.domain.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Getter
public class AdministradorDoSistema {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Embedded private DadosPessoais dadosPessoais;

    @OneToOne(cascade = CascadeType.REMOVE) private Usuario usuario;

    public AdministradorDoSistema(DadosPessoais dadosPessoais, Usuario usuario) {

        this.dadosPessoais = dadosPessoais;
        this.usuario = usuario;
    }

    public String getNome() {

        return this.dadosPessoais.getNome();
    }

    public String getCpf() {

        return this.dadosPessoais.getCpf();
    }

    public String getTelefone() {

        return this.dadosPessoais.getTelefone();
    }

    public LocalDate getDataNasc() {

        return this.dadosPessoais.getDataNasc();
    }

    public Long getIdUsuario() {

        return this.usuario.getId();
    }

    public String getEmail() {

        return this.usuario.getEmail();
    }

}
