package estudos.ecommerce.controller.response;

import estudos.ecommerce.cliente.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class ClienteResponse {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNasc;

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getDadosPessoais().getNome();
        this.cpf = cliente.getDadosPessoais().getCpf();
        this.telefone = cliente.getDadosPessoais().getTelefone();
        this.dataNasc = cliente.getDadosPessoais().getDataNasc();
    }

    public static List<ClienteResponse> from(List<Cliente> clientes) {
        return clientes.stream().map(ClienteResponse::new).collect(Collectors.toList());
    }
}
