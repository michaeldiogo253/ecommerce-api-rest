package estudos.ecommerce.cliente.adapter.in.web;

import estudos.ecommerce.cliente.adapter.in.web.request.ClienteRequest;
import estudos.ecommerce.cliente.application.port.in.CadastrarClienteUseCase;
import estudos.ecommerce.cliente.domain.Cliente;
import estudos.ecommerce.cliente.adapter.in.web.response.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@Transactional
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ecommerce-api/cliente")
public class CadastrarClienteController {

    private final CadastrarClienteUseCase useCase;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody @Valid ClienteRequest request,
                                                            UriComponentsBuilder uriBuilder) {

        Cliente clienteCriado = useCase.execute(request.toModel());

        URI uri = uriBuilder.path("ecommerce-api/cliente/{id}")
                            .buildAndExpand(clienteCriado.getId())
                            .toUri();

        return ResponseEntity.created(uri).body(new ClienteResponse(clienteCriado));

    }

}
