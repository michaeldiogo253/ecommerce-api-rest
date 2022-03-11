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

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(ClienteRequest request) {

        LocalDate dataFormatada = FormatadorDeDatas.converteParaPadraoBrasil(request.getDataNasc());

        Cliente cliente = new Cliente(request.getNome(), request.getCpf(), request.getTelefone(), dataFormatada);
        clienteRepository.save(cliente);
        return cliente;

    }

    public Optional<Cliente> buscarClientePorId(Long id) {

        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
        return clienteBuscado;

    }

    public List<Cliente> listarTodosOsClientes() {

        List<Cliente> clientes = clienteRepository.findAll();

        return clientes;

    }

    public ResponseEntity<?> atualizarClientePorId(Long id, ClienteRequest request) {

        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
        LocalDate dataFormatada = FormatadorDeDatas.converteParaPadraoBrasil(request.getDataNasc());

        if (clienteBuscado.isPresent()) {
            clienteBuscado.get()
                          .setDadosPessoais(new DadosPessoais(request.getNome(),
                                                              request.getCpf(),
                                                              request.getTelefone(),
                                                              dataFormatada));

            return ResponseEntity.ok()
                                 .body(new ClienteResponse(clienteBuscado.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Cliente não encontrado");
    }

    public ResponseEntity<Void> deletarClientePorId(Long id) {

        Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
        if (clienteBuscado.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent()
                                 .build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .build();

    }
}
