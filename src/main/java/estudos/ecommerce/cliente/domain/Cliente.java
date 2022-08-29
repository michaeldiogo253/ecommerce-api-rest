package estudos.ecommerce.cliente.domain;

import estudos.ecommerce.usuario.domain.Perfil;
import estudos.ecommerce.usuario.domain.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clientes")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Embedded private DadosPessoais dadosPessoais;

    @Embedded private Endereco endereco;

    @OneToOne(cascade = CascadeType.REMOVE) private Usuario usuario;

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNasc, Endereco endereco, Usuario usuario) {

        this.dadosPessoais = new DadosPessoais(nome, cpf, telefone, dataNasc);
        this.endereco = endereco;
        this.usuario = usuario;
    }

    @Override
    public String toString() {

        return "Cliente{" + "id=" + id + ", dadosPessoais=" + dadosPessoais + '}';
    }

    public String getNome() {

        return this.dadosPessoais.getNome();
    }


    public String getDataNascimentoString() {

        LocalDate dataNasc = this.dadosPessoais.getDataNasc();
        return dataNasc.toString();
    }

    public void atualizaDadosPessoais(DadosPessoais dadosPessoaisASerAtualizado) {

        this.dadosPessoais.setNome(dadosPessoaisASerAtualizado.getNome());
        this.dadosPessoais.setCpf(dadosPessoaisASerAtualizado.getCpf());
        this.dadosPessoais.setTelefone(dadosPessoaisASerAtualizado.getTelefone());
        this.dadosPessoais.setDataNasc(dadosPessoaisASerAtualizado.getDataNasc());
    }

    public void atualizaEndereco(Endereco endereco) {

        this.getEndereco()
            .atualizaEndereco(endereco);

    }

    public void atribuiPerfil(Perfil perfil){
        usuario.atribuiPerfil(perfil);
    }

}