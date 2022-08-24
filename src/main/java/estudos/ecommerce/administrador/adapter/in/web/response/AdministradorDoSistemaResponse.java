package estudos.ecommerce.administrador.adapter.in.web.response;

import estudos.ecommerce.administrador.domain.AdministradorDoSistema;
import lombok.Value;

import java.time.LocalDate;

@Value
public class AdministradorDoSistemaResponse {

    String nome;
    String cpf;
    String telefone;
    LocalDate dataNasc;
    Long idUsuario;
    String email;

    public AdministradorDoSistemaResponse(AdministradorDoSistema administradorDoSistema) {

        this.nome = administradorDoSistema.getNome();
        this.cpf = administradorDoSistema.getCpf();
        this.telefone = administradorDoSistema.getTelefone();
        this.dataNasc = administradorDoSistema.getDataNasc();
        this.idUsuario = administradorDoSistema.getIdUsuario();
        this.email = administradorDoSistema.getEmail();
    }

}
