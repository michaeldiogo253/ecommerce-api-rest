package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.adapter.in.web.response.ClienteResponse;
import estudos.ecommerce.cliente.application.port.in.ListarTodosOsClientesUseCase;
import estudos.ecommerce.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/cliente")
public class ListarTodosOsClientesController {

    private final ListarTodosOsClientesUseCase listarTodosOsClientesUseCase;

    @GetMapping("/listar-todos")
    public ResponseEntity<List<ClienteResponse>> listaClientePorId(@PathVariable Long idCliente){

        List<Cliente> clientes = listarTodosOsClientesUseCase.execute();

        return ResponseEntity.ok().body(ClienteResponse.from(clientes));
    }
}
