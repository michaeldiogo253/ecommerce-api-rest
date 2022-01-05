package estudos.ecommerce.service;

import estudos.ecommerce.controller.request.ClienteRequest;
import estudos.ecommerce.controller.response.ClienteResponse;
import estudos.ecommerce.model.Cliente;
import estudos.ecommerce.model.DadosPessoais;
import estudos.ecommerce.repository.ClienteRepository;
import estudos.ecommerce.util.FormatadorDeDatas;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ResponseEntity<ClienteResponse> cadastrarCliente(ClienteRequest request, UriComponentsBuilder uriBuilder) {
        LocalDate dataFormatada = FormatadorDeDatas.converteParaPadraoBrasil(request.getDataNasc());

        Cliente cliente = new Cliente(request.getNome(), request.getCpf(), request.getTelefone(), dataFormatada);
        clienteRepository.save(cliente);
        URI uri = uriBuilder.path("ecommerce-api/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteResponse(cliente));
    }

    public ResponseEntity<?> buscarClientePorId(Long id) {
        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
        if (clienteBuscado.isPresent()) {
            return ResponseEntity.ok().body(new ClienteResponse(clienteBuscado.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    public ResponseEntity<List<ClienteResponse>> listarTodosOsClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponse> clientesResponse = ClienteResponse.from(clientes);
        return ResponseEntity.ok().body(clientesResponse);
    }

    public ResponseEntity<?> atualizarClientePorId(Long id, ClienteRequest request) {
        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
        LocalDate dataFormatada = FormatadorDeDatas.converteParaPadraoBrasil(request.getDataNasc());

        if (clienteBuscado.isPresent()) {
            clienteBuscado.get().setDadosPessoais(
                    new DadosPessoais(request.getNome(), request.getCpf(), request.getTelefone(), dataFormatada));

            return ResponseEntity.ok().body(new ClienteResponse(clienteBuscado.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    public ResponseEntity<?> deletarClientePorId(Long id) {
        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
        if (clienteBuscado.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }
}
