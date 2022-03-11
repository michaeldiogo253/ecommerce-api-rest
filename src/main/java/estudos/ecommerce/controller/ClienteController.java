package estudos.ecommerce.controller;

import estudos.ecommerce.controller.request.ClienteRequest;
import estudos.ecommerce.controller.response.ClienteResponse;
import estudos.ecommerce.model.Cliente;
import estudos.ecommerce.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("ecommerce-api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody @Valid ClienteRequest request, UriComponentsBuilder uriBuilder) {

        Cliente clienteSalvo = clienteService.cadastrarCliente(request);
        URI uri = uriBuilder.path("ecommerce-api/cliente/{id}")
                            .buildAndExpand(clienteSalvo.getId())
                            .toUri();

        return ResponseEntity.created(uri)
                             .body(new ClienteResponse(clienteSalvo));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {

        Optional<Cliente> clienteBuscado = clienteService.buscarClientePorId(id);
        if (clienteBuscado.isPresent()) {
            return ResponseEntity.ok()
                                 .body(new ClienteResponse(clienteBuscado.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Cliente não encontrado");

    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<ClienteResponse>> listarTodosOsClientes() {

        List<Cliente> clientes = clienteService.listarTodosOsClientes();
        List<ClienteResponse> clientesResponse = ClienteResponse.from(clientes);

        return ResponseEntity.ok()
                             .body(clientesResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarClientePorId(@PathVariable Long id, @RequestBody ClienteRequest request) {

        return clienteService.atualizarClientePorId(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClientePorId(@PathVariable Long id) {

        return clienteService.deletarClientePorId(id);
    }
}
