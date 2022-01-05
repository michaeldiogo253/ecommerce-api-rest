package estudos.ecommerce.controller;

import estudos.ecommerce.controller.request.ClienteRequest;
import estudos.ecommerce.controller.response.ClienteResponse;
import estudos.ecommerce.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("ecommerce-api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Transactional
    @PostMapping()
    public ResponseEntity<ClienteResponse> cadastrarCliente(
            @RequestBody @Valid ClienteRequest request, UriComponentsBuilder uriBuilder) {

        return clienteService.cadastrarCliente(request, uriBuilder);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id);
    }

    @Transactional
    @GetMapping("/listar-todos")
    public ResponseEntity<List<ClienteResponse>> listarTodosOsClientes() {
        return clienteService.listarTodosOsClientes();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarClientePorId(@PathVariable Long id, @RequestBody ClienteRequest request){
        return clienteService.atualizarClientePorId(id , request);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarClientePorId(@PathVariable Long id){
        return clienteService.deletarClientePorId(id);
    }
}
