package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.adapter.in.web.request.ClienteRequest;
import estudos.ecommerce.cliente.adapter.in.web.response.ClienteResponse;
import estudos.ecommerce.cliente.application.port.in.AtualizaDadosPessoaisClienteUseCase;
import estudos.ecommerce.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/cliente")
public class AtualizaClienteController {

    private final AtualizaDadosPessoaisClienteUseCase atualizaDadosPessoaisClienteUseCase;

    @PutMapping("/atualiza/{idCliente}")
    public ResponseEntity<ClienteResponse> atualizaCliente(@PathVariable Long idCliente,
                                                           @RequestBody @Valid ClienteRequest request){

        Cliente clienteAtualizado = atualizaDadosPessoaisClienteUseCase.execute(idCliente, request.toDadosPessoais(request));

        return ResponseEntity.ok().body(new ClienteResponse(clienteAtualizado));
    }

}
