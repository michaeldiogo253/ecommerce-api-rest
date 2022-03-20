package estudos.ecommerce.cliente.adapter.in.web.response;

import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.util.data.ConversorDeDatas;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ClienteResponse {

    String nome;
    String cpf;
    String telefone;
    String dataNasc;

    public ClienteResponse(Cliente cliente) {

        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.dataNasc = ConversorDeDatas.converteLocalDateParaString(cliente.getDataNascimento());
    }

    public static List<ClienteResponse> from (List<Cliente> clientes){

        return clientes.stream().map(ClienteResponse::new).collect(Collectors.toList());

    }
}
