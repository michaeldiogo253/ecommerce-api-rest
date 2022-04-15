package estudos.ecommerce.cliente.domain;

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

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNasc) {

        this.dadosPessoais = new DadosPessoais(nome, cpf, telefone, dataNasc);

    }

    @Override
    public String toString() {

        return "Cliente{" + "id=" + id + ", dadosPessoais=" + dadosPessoais + '}';
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

    public LocalDate getDataNascimento() {

        return this.dadosPessoais.getDataNasc();
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
}