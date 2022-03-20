package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.adapter.in.web.response.ClienteResponse;
import estudos.ecommerce.cliente.application.port.in.ListarClientePorIdControllerUseCase;
import estudos.ecommerce.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/cliente")
public class ListarClientePorIdController {

    private final ListarClientePorIdControllerUseCase listarClientePorIdControllerUseCase;

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteResponse> listaClientePorId(@PathVariable Long idCliente){

        Cliente clienteBuscado = listarClientePorIdControllerUseCase.execute(idCliente);

        return ResponseEntity.ok().body(new ClienteResponse(clienteBuscado));
    }
}
